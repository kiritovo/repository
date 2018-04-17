package com.ice.service;

import com.ice.dto.OrderDTO;

/**
 * 支付
 * @author 雪糕
 *
 */
public interface PayService {
  
	  void create(OrderDTO orderDTO);
}
