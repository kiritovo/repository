package com.ice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ice.converter.OrderFormTOrderDTO;
import com.ice.dto.OrderDTO;
import com.ice.enums.ResultEnum;
import com.ice.exception.SellException;
import com.ice.form.OrderForm;
import com.ice.service.BuyerService;
import com.ice.service.OrderService;
import com.ice.util.ResultVOUtil;
import com.ice.vo.ResultVO;





@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {
	private final Logger logger = LoggerFactory.getLogger(BuyerOrderController.class);
	@Autowired
	private OrderService service;
	
	@Autowired
    private BuyerService byservice;	
	/**
	 * 创建订单
	 */
	@RequestMapping("/create")
	public ResultVO<Map<String,String>> create(@Valid OrderForm odrForm,BindingResult bindResult){
		if(bindResult.hasErrors()){
			logger.error("[创建订单] 入参不正确 orderForm={}",odrForm);
			throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindResult.getFieldError().getDefaultMessage());	
		}
		OrderDTO odrDTO= OrderFormTOrderDTO.convert(odrForm);
		if(CollectionUtils.isEmpty(odrDTO.getOrderDetailList())){
			logger.error("[创建订单]转化后 购物车不能为空");
			throw new SellException(ResultEnum.CART_EMPTY);
		}
		
		OrderDTO createResult = service.create(odrDTO);
		Map<String,String> map = new HashMap<>();
		map.put("orderId",createResult.getOrderId());
		
		return  ResultVOUtil.success(map);
	}
	
	/**
	 * 订单列表
	 */
	@GetMapping("/list")
	public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
			                             @RequestParam(value ="page" ,defaultValue="0") Integer page,
		                                 @RequestParam(value="size", defaultValue="10") Integer size){
		if(StringUtils.isEmpty(openid)){
			logger.error("[订单查询列表] openid为空");
			throw new SellException(ResultEnum.PARAM_ERROR);
	
		}
		PageRequest request=new PageRequest(page, size);
		Page<OrderDTO> orderDTOPage =service.findList(openid, request);
		//orderDTOPage.getTotalElements(); 返回总数用到   orderDTOPage.getTotalPages()  返回总页数
		return ResultVOUtil.success(orderDTOPage.getContent());  
	}
	
	/**
	 * 订单详情
	 */
	
	@GetMapping("/detail")
	public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
			                         @RequestParam("orderId")String orderId){
		if(StringUtils.isEmpty(openid)){
			logger.error("[订单详情] openid为空");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		if(StringUtils.isEmpty(orderId)){
			logger.error("[订单详情] orderId为空");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
        //这个方法里判断是否登录人与查询订单用户是一个openid 
		OrderDTO odrDTO =  byservice.findOrderOne(openid, orderId);

		return ResultVOUtil.success(odrDTO);
	}
	/**
	 * 取消订单
	 */
	@PostMapping("cancel")
	public ResultVO cancel (@RequestParam("openid") String openid,
            				@RequestParam("orderId")String orderId){
		if(StringUtils.isEmpty(openid)){
			logger.error("[订单详情] openid为空");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		if(StringUtils.isEmpty(orderId)){
			logger.error("[订单详情] orderId为空");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		//TODO  安全性问题 没有判断openid的有无
		 byservice.cancelOrderOne(openid, orderId);
		  
		 return ResultVOUtil.success();
	}

}
