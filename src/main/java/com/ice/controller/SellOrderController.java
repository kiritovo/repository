package com.ice.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ice.dto.OrderDTO;
import com.ice.enums.ResultEnum;
import com.ice.exception.SellException;
import com.ice.service.OrderService;

@Controller
@RequestMapping("/order")
public class SellOrderController {
	
	 private final Logger logger = LoggerFactory.getLogger(SellOrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 订单列表
	 * @param page 第几页 第一页开始
	 * @param size 每页的数据数
	 * @return ModelAndView 用到模板 freemarker
	 */
      @GetMapping("/list")  
	  public ModelAndView list(@RequestParam(value="page",defaultValue="1") Integer page ,
			                   @RequestParam(value="size",defaultValue="5") Integer size ,
			                   Map<String,Object> map){
    	  PageRequest pageRequest =new PageRequest(page - 1, size);
    	  Page<OrderDTO> orderDTOPage =orderService.findList(pageRequest);
    	  map.put("orderDTOPage", orderDTOPage);
    	  map.put("currentPage", page);
    	  map.put("size",size);
    	  return new ModelAndView("order/list",map);
		  
	  }
      /**
       * 后台取消订单的方法 
       * @param orderId
       * @return 重新跳到之前的页面
       */
      @GetMapping("/cancel")
      public ModelAndView cancel(@RequestParam("orderId") String orderId,
    		                      Map<String,Object> map){
    	  try {
    		  OrderDTO orderDTO =orderService.findOne(orderId);
    	      orderService.cancel(orderDTO);
			} catch (SellException e) {
				 logger.error("[卖家端取消订单] 发生异常{}",e);
	    		 map.put("msg", e.getMessage());
	    		 map.put("url", "/sell/order/list");
	    		 return new ModelAndView("common/error", map);
			}
	    	  
    	  map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
    	  map.put("url", "/sell/order/list");
    		
    	  
    	
    	  return new ModelAndView("common/success",map);
      }
}
