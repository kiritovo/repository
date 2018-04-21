package com.ice.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ice.LogTest;
import com.ice.converter.OrderMasterTOrderDTO;
import com.ice.dao.OrderDetailDao;
import com.ice.dao.OrderMasterDao;
import com.ice.dto.OrderDTO;
import com.ice.dto.ShopCarDTO;
import com.ice.entity.OrderDetail;
import com.ice.entity.OrderMaster;
import com.ice.entity.ProductInfo;
import com.ice.enums.OrderStatusEnum;
import com.ice.enums.PayStatusEnum;
import com.ice.enums.ResultEnum;
import com.ice.exception.SellException;
import com.ice.service.OrderService;
import com.ice.service.ProductService;
import com.ice.util.KeyUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderDetailDao detailDao;
	
	@Autowired
	private OrderMasterDao masterDao;
	
	
	BigDecimal orderAmount =new BigDecimal(BigInteger.ZERO);
	
	private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
/**
 * 创建
 */
	@Override
	@Transactional
	public OrderDTO create(OrderDTO orderDTO) {
		
		String orderId =KeyUtil.genUniqueKey();
		
		//1.查询商品的    库存数量 价格
		 for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){
			 ProductInfo productInfo =productService.findOne(orderDetail.getProductId());
			 if(productInfo ==null){
				 throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);  //抛出自定义商品不存在异常
			 }
		//2.计算总价     一件物品的总价然后加上之前的总价的和
			 orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount) ;
        //订单详情入库
			 orderDetail.setDetailId(KeyUtil.genUniqueKey());
			 orderDetail.setOrderId(orderId); //订单创建时候就生成了
			 
			 BeanUtils.copyProperties(productInfo, orderDetail); //spring提供的 直接把对象的属性传到另一个实体中
			 
			 detailDao.save(orderDetail);
		 }
			
		//3.ordermaster 
		 OrderMaster orderMaster =new OrderMaster();
		
		 orderDTO.setOrderId(orderId); 
		 
	     BeanUtils.copyProperties(orderDTO,orderMaster);  //拷贝完再传值 因为null也会拷贝 然后覆盖你传的值
	     
	    
	    // orderMaster.setOrderId(orderId);
		 orderMaster.setOrderAmount(orderAmount);
		 orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
		 orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
	     
	     masterDao.save(orderMaster);
		
		//4.购买成功,库存减少 这里判断库存够不够
	    
//	     java8 lambda 表达式  等同于定义实体ShopCarDTO 然后把属性通过构造方法放进去 然后在add进集合
	     List<ShopCarDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
         new ShopCarDTO(e.getProductId(), e.getProductQuantity())
	     ).collect(Collectors.toList());
	     
	     productService.decreaseStock(cartDTOList);
	     
		return orderDTO; 
	}
/**
 * 查询
 */
	@Override
	public OrderDTO findOne(String orderId) {
		OrderMaster orderMaster =masterDao.findOne(orderId);
		if(orderMaster ==null){
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		//查询订单详情
		List<OrderDetail> orderDetailList = detailDao.findByOrderId(orderId);
		if(CollectionUtils.isEmpty(orderDetailList)){
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
		}
		
		OrderDTO orderDTO =new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		orderDTO.setOrderDetailList(orderDetailList);
		
		return orderDTO;
	}

	@Override
	public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
	
		Page<OrderMaster> orderMasterPage =masterDao.findByBuyerOpenid(buyerOpenid, pageable);
		List<OrderDTO> orderDTOList = OrderMasterTOrderDTO.convert(orderMasterPage.getContent());
		Page<OrderDTO> orderDTOPage =new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());//入参是集合+分页格式+一共多少个元素(集合元素个数
		
	    return orderDTOPage;
	}
/**
 * 取消订单的方法
 */
	@Override
	@Transactional 
	public OrderDTO cancel(OrderDTO orderDTO) {
		OrderMaster orderMaster =new OrderMaster();
		
		
		
		//判断订单状态  已经被接单 就不能取消
		if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
			logger.error("[取消订单]订单状态不正确 , orderId={} ,orderStatus={}",orderDTO.getOrderId(),orderDTO.getPayStatus());
			throw new SellException(ResultEnum.ORDER_STASUS_ERROR);
		}
		//修改订单状态
		orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
		BeanUtils.copyProperties(orderDTO, orderMaster);//参数都传到了之后才可以进行拷贝 不然会中间少一些属性
		OrderMaster  updateResult = masterDao.save(orderMaster);
		if(updateResult==null){
			logger.error("[取消订单]更新失败,orderMaster={}",orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		
		//库存返还
		if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
			logger.error("[取消订单]订单中无商品详情,orderDTO={}",orderDTO);
			throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);

		}
		List<ShopCarDTO> shopCarDTOList=orderDTO.getOrderDetailList().stream()
                .map(e -> new ShopCarDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
		
		productService.increaseStock(shopCarDTOList);
		//已支付要给用户退款
		if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
			
			//TODO
		}
		return orderDTO;
	}
/**
 * 完结订单的方法
 */
	@Override
	@Transactional
	public OrderDTO finish(OrderDTO orderDTO) {
	  //判断订单状态
		if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
			logger.error("[完结订单] 订单不正确,orderId={} ,orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STASUS_ERROR);
		}
	  //修改状态
		orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		
		OrderMaster updateResult =masterDao.save(orderMaster);
		if(updateResult==null){
			logger.error("[取消订单]更新失败,orderMaster={}",orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		
		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO paid(OrderDTO orderDTO) {
		//判断订单状态
		if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
			logger.error("[订单支付] 订单状态不正确,orderId={} ,orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STASUS_ERROR);
		}
		//判断支付状态
		if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
			logger.error("[订单支付完成]订单支付状态不正确,orderDTO={}",orderDTO);
			throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
		}
		//修改支付状态
		orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		
		OrderMaster updateResult =masterDao.save(orderMaster);
		if(updateResult==null){
			logger.error("[订单支付完成]更新失败,orderMaster={}",orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		
		return orderDTO;
	}
	@Override
	public Page<OrderDTO> findList(Pageable pageable) {
		Page<OrderMaster> orderMasterPage = masterDao.findAll(pageable);
		List<OrderDTO> orderDTOList = OrderMasterTOrderDTO.convert(orderMasterPage.getContent());
		Page<OrderDTO> orderDTOPage =new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());//入参是集合+分页格式+一共多少个元素(集合元素个数
		
	    return orderDTOPage;
	}

}
