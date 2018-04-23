package com.ice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ice.dto.OrderDTO;
import com.ice.service.OrderService;

@Controller
@RequestMapping("/sell/order")
public class SellOrderController {
	
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
			                   @RequestParam(value="size",defaultValue="10") Integer size ,
			                   Map<String,Object> map){
    	  PageRequest pageRequest =new PageRequest(page - 1, size);
    	  Page<OrderDTO> orderDTOPage =orderService.findList(pageRequest);
    	  map.put("orderDTOPage", orderDTOPage);
    	  return new ModelAndView("order/list",map);
		  
	  }
}
