package com.ice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ice.PayServiceImplTest;
import com.ice.dto.OrderDTO;
import com.ice.service.PayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

@Service
public class PayServiceImpl implements PayService {

	private final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);
	
	private static final String ORDE_NAME="微信订餐订单";
	
	@Autowired
	private BestPayServiceImpl bestPayService;
	@Override
	public void create(OrderDTO orderDTO) {
		PayRequest payRequest =new PayRequest();
		payRequest.setOpenid(orderDTO.getBuyerOpenid());
		payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
		payRequest.setOrderId(orderDTO.getOrderId());
		payRequest.setOrderName(ORDE_NAME);
		payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);//支付方式的选择   这里用微信h5
		logger.info("[微信支付]  request={}",payRequest);
		PayResponse payResponse = bestPayService.pay(payRequest);
		logger.info("[微信支付 response] ={}",payResponse);
		
		
		
	}

    
}
