package com.ice.converter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ice.dto.OrderDTO;
import com.ice.entity.OrderDetail;
import com.ice.enums.ResultEnum;
import com.ice.exception.SellException;
import com.ice.form.OrderForm;

public class OrderFormTOrderDTO {
	private static final Logger logger = LoggerFactory.getLogger(OrderFormTOrderDTO.class);
    public  static OrderDTO  convert (OrderForm orderForm){
	  
	  Gson gson = new Gson();
      OrderDTO orderDTO = new OrderDTO();

      orderDTO.setBuyerName(orderForm.getName());
      orderDTO.setBuyerPhone(orderForm.getPhone());
      orderDTO.setBuyerAddress(orderForm.getAddress());
      orderDTO.setBuyerOpenid(orderForm.getOpenid());

      List<OrderDetail> orderDetailList = new ArrayList<>();
      try {
          orderDetailList = gson.fromJson(orderForm.getItems(),
                  new TypeToken<List<OrderDetail>>() {
                  }.getType());
      } catch (Exception e) {
         logger.error("【对象转换】错误, string={}", orderForm.getItems());
          throw new SellException(ResultEnum.PARAM_ERROR);
      }
      orderDTO.setOrderDetailList(orderDetailList);

      return orderDTO;
  }
 
}
