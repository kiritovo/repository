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

import com.ice.entity.ProductInfo;
import com.ice.exception.SellException;
import com.ice.service.ProductService;


/**
 * 卖家端商品
 * @author 雪糕
 * 
 */
@Controller
@RequestMapping("/product")
public class SellerProductController {
     
	 @Autowired
	  private ProductService productService;
	
	  /**
	   * 商品列表
	   * @param page
	   * @param size
	   * @param map
	   * @return
	   */
	  @GetMapping("/list")
	  public ModelAndView List (@RequestParam(value="page",defaultValue="1") Integer page ,
      @RequestParam(value="size",defaultValue="3") Integer size ,
      Map<String,Object> map){
		  
		  PageRequest request= new  PageRequest(page-1, size);
		  Page<ProductInfo> productInfoPage =productService.findAll(request);
		  map.put("productInfoPage",productInfoPage);
    	  map.put("currentPage", page);
    	  map.put("size",size);
		  return new ModelAndView("product/list");
	  }
	  
	  /**
	   * 商品上架
	   * @param productId
	   * @return
	   */
	  @GetMapping("/on_sale")
	  public ModelAndView onSale(@RequestParam("productId") String productId,
			                    Map<String,Object> map){
		  try {
			  productService.onSale(productId);
		} catch (SellException e) {
			map.put("msg", e.getMessage());
            map.put("url", "/sell/product/list");
            return new ModelAndView("common/error",map);
		}
		  
		  map.put("url", "/sell/product/list");
		  return new ModelAndView("common/success",map);
	  } 
	  /**
	   * 商品下架
	   * @param productId
	   * @return
	   */
	  @GetMapping("/off_sale")
	  public ModelAndView offSale(@RequestParam("productId") String productId,
			                    Map<String,Object> map){
		  try {
			  productService.offSale(productId);
		} catch (SellException e) {
			map.put("msg", e.getMessage());
            map.put("url", "/sell/product/list");
            return new ModelAndView("common/error",map);
		}
		  
		  map.put("url", "/sell/product/list");
		  return new ModelAndView("common/success",map);
	  } 
}
