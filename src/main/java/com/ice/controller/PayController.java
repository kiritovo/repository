package com.ice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ice.dto.OrderDTO;
import com.ice.enums.ResultEnum;
import com.ice.exception.SellException;
import com.ice.service.OrderService;

@Controller
@RequestMapping("/pay")
public class PayController {
  
	@Autowired
	private OrderService orderService;
	
	
	/**
    * 支付传订单自己查并不能让用户直接传钱 防止他修改
    * @param orderId
    * @param returnUrl
    */
	@GetMapping("/create")
	public void create(@RequestParam("orderId") String orderId,
			           @RequestParam("returnUrl") String returnUrl){
		//查询订单
		OrderDTO orderDTO =orderService.findOne(orderId);
		if(orderDTO ==null){
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		//支付
	}
}
