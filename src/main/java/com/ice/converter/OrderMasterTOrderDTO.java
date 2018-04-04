package com.ice.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.ice.dto.OrderDTO;
import com.ice.entity.OrderMaster;
/**
 * 二者转化的方法
 * @author 雪糕
 *
 */
public class OrderMasterTOrderDTO {
   
	  public static OrderDTO convert(OrderMaster orderMaster){
		  OrderDTO orderDTO =new OrderDTO();
		  BeanUtils.copyProperties(orderMaster, orderDTO);
		  return orderDTO;
	  }
	  
	  public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
		    return orderMasterList.stream().map(e ->
            convert(e)
		   ).collect(Collectors.toList());
	  }
}
