package com.ice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ice.dto.OrderDTO;
import com.ice.enums.ResultEnum;
import com.ice.exception.SellException;
import com.ice.service.BuyerService;
import com.ice.service.OrderService;
@Service
public class BuyerServiceImpl implements BuyerService {
   
	private final Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class); 
	
	@Autowired
	private OrderService service;
	 
	@Override
	public OrderDTO findOrderOne(String openid, String orderId) {
		return checkOrderOwner(openid,orderId);
	}

	@Override
	public OrderDTO cancelOrderOne(String openid, String orderId) {
		OrderDTO odrDTO =checkOrderOwner(openid,orderId);
		if(odrDTO ==null){
			logger.error("[取消订单] 查不到该订单 orderId={}",orderId);
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		return service.cancel(odrDTO);
	}

	
	private OrderDTO checkOrderOwner(String openid, String orderId){
		OrderDTO odrDTO = service.findOne(orderId);
		if(odrDTO ==null){
			return null;
		}
		//判断是否是自己的订单
		if(!odrDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
			logger.error("[查询订单] 订单的openid不一致 .openid={} ,odrDTO={}",openid,orderId);
			throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
		}
		return odrDTO;
	}
}
