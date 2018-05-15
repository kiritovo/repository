package com.ice.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ice.entity.ProductCategory;
import com.ice.entity.ProductInfo;
import com.ice.exception.SellException;
import com.ice.form.ProductForm;
import com.ice.service.CateGoryService;
import com.ice.service.ProductService;
import com.ice.util.KeyUtil;




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
	
	 @Autowired
	  private CateGoryService categoryService;
	 
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
	  
	  
	  @GetMapping("/index")
	  public ModelAndView index(@RequestParam(value = "productId" ,required=false) String productId,
              Map<String,Object> map){
		  
		  if(!StringUtils.isEmpty(productId)){
		      ProductInfo  productInfo =productService.findOne(productId);
		      map.put("productInfo", productInfo);
  
		  }
		//查询出所有类目 下拉框需要
		  List<ProductCategory> categoryList =categoryService.findAll();
		  map.put("categoryList", categoryList);
		 return new ModelAndView("product/index",map);
	  }
	  
	  /**
	   * 保存和更新商品的方法
	   * @param form
	   * @param bindingResult
	   * @param map
	   * @return
	   */
	  @PostMapping("/save")
	  public ModelAndView save(@Valid ProductForm form,
			                  BindingResult bindingResult,
			                  Map<String,Object> map){
		  if(bindingResult.hasErrors()){
			   map.put("msg",bindingResult.getFieldError().getDefaultMessage());
			   map.put("url","sell/product/index");
			   return new ModelAndView("common/error" ,map);
		  }
		  
		  ProductInfo productInfo =new ProductInfo();
		  try {
			 
             if(!StringUtils.isEmpty(form.getProductId())){
            	 //修改就直接查出来赋值不用重新new
            	 productInfo=productService.findOne(form.getProductId());	 
               }else{
            	 //新增要给他一个id
                     form.setProductId(KeyUtil.genUniqueKey());
               }
             
			  BeanUtils.copyProperties(form, productInfo);
			  productService.save(productInfo);	
		} catch (SellException e) {
			   map.put("msg",e.getMessage());
			   map.put("url","sell/product/index");
			   return new ModelAndView("common/error" ,map);

		}
		 
		  	map.put("url", "/sell/product/list");
	        return new ModelAndView("common/success", map);
	  }
}
