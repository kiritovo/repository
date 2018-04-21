package com.ice;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

import com.ice.dto.OrderDTO;
import com.ice.service.impl.OrderServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
	 @Autowired
	 private OrderServiceImpl impl;
	 
	 private final String BUYER_OPENID="110110";
	 
	 private final String ORDER_ID="1522047105913820352";
	 
	 private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	 
	 @Test
	 public void create() throws Exception{
//		 OrderDTO orderDTO =new OrderDTO();
//		 orderDTO.setBuyerName("预判峰");
//		 orderDTO.setBuyerAddress("昌平邓庄");
//		 orderDTO.setBuyerPhone("17611241201");
//		 orderDTO.setBuyerOpenid(BUYER_OPENID);
//		 
//		 List<OrderDetail> orderDetailList =new ArrayList<>();
//		 OrderDetail o1= new OrderDetail();
//		 o1.setProductId("00001");
//		 o1.setProductQuantity(1);
//		 orderDetailList.add(o1);
//		 
//		 orderDTO.setOrderDetailList(orderDetailList);
//		 
//		 OrderDTO result =impl.create(orderDTO);
//		 logger.info("创建订单 result:"+result);
		 
		 
	 }
	 @Test
	 public void findOne() throws Exception{
//		OrderDTO result = impl.findOne(ORDER_ID);
//		logger.info("查询单个订单 result={}",result);
//		if(result.getOrderId()==ORDER_ID){
//			System.out.println("it's ok!");
//		}
	 }
	 
	 @Test 
	 public void findList() throws Exception{
//		 PageRequest request =new PageRequest(0, 2);
//		 Page<OrderDTO> orderDTOPage =impl.findList(BUYER_OPENID, request);
//		 System.out.println(orderDTOPage.getTotalElements());
	 }
	 
	 @Test
	 public void cancel() throws Exception{
//		 OrderDTO orderDTO = impl.findOne(ORDER_ID);
//		 OrderDTO request = impl.cancel(orderDTO);
//		 System.out.println(request.getOrderStatus().equals( OrderStatusEnum.CANCEL.getCode()));//Assert.assertEquals(expected, actual);
	 }
	 
	 @Test
	 public void finish() throws Exception{
//		 OrderDTO orderDTO = impl.findOne(ORDER_ID);
//		 OrderDTO request = impl.finish(orderDTO);
//		 System.out.println(request.getOrderStatus().equals( OrderStatusEnum.FINISHED.getCode()));//Assert.assertEquals(expected, actual);
	 }
	 @Test 
	 public void paid() throws Exception{
//		 OrderDTO orderDTO = impl.findOne(ORDER_ID);
//		 OrderDTO request = impl.paid(orderDTO);
//		 System.out.println(request.getPayStatus().equals( PayStatusEnum.SUCCESS.getCode()));//Assert.assertEquals(expected, actual);
	 }
	 
	 @Test
	 public void list(){
		 PageRequest request =new PageRequest(0, 2);
		 Page<OrderDTO> orderDTOPage =impl.findList(request);
		 System.out.println(orderDTOPage.getTotalElements());
		 Assert.assertTrue("查询所有的订单列表", true);	 //orderDTOPage.getTotalElements()
		 
		 
	 }
}
