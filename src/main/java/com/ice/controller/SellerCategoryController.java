package com.ice.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ice.entity.ProductCategory;
import com.ice.exception.SellException;
import com.ice.form.CategoryForm;
import com.ice.service.CateGoryService;

/**
 * 
 * @author 雪糕
 *
 */
@Controller
@RequestMapping("/category")
public class SellerCategoryController {
     
	@Autowired
	private CateGoryService categoryService;
	/**
	 * 类目列表
	 * @param map
	 * @return
	 */
	@GetMapping("/list")
	 public ModelAndView list(Map<String, Object> map){
		 
		 List<ProductCategory> categoryList =categoryService.findAll();
		 map.put("categoryList", categoryList);
		 return  new  ModelAndView("category/list",map);
	}
	
	/**
	 * 修改state和名称的界面
	 * @param categoryId
	 * @param map
	 * @return
	 */
	@GetMapping("/index")
	public ModelAndView index(@RequestParam(value ="categoryId",required=false) Integer categoryId,
			                 Map<String,Object> map){
		 if(categoryId != null){
			  ProductCategory productCategory = categoryService.findOne(categoryId);
			  map.put("productCategory", productCategory);
			  
		 }
		 return new ModelAndView("category/index",map);
	}
	/**
	 * 新增类目/更新   state 、名称
	 * @param form
	 * @param bindingResult
	 * @param map
	 * @return
	 */
	@PostMapping("/save")
	 public ModelAndView save(@Valid CategoryForm form,
			            BindingResult bindingResult,
			            Map<String, Object> map){
		 if(bindingResult.hasErrors()){
			   map.put("msg",bindingResult.getFieldError().getDefaultMessage());
			   map.put("url","sell/category/index");
			   return new ModelAndView("common/error" ,map);
		  }
		 ProductCategory productCategory =new  ProductCategory();
		 try {
			 if(form.getCategoryId() !=null){
				   productCategory = categoryService.findOne(form.getCategoryId());
			 }
			 BeanUtils.copyProperties(form, productCategory);
			 categoryService.save(productCategory);
		} catch (SellException e) {
			   map.put("msg",e.getMessage());
			   map.put("url","sell/category/index");
			   return new ModelAndView("common/error" ,map);
		}
		 map.put("url", "/sell/category/list");
	     return new ModelAndView("common/success", map);
	}
}
