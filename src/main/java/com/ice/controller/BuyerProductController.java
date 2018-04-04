package com.ice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ice.entity.ProductCategory;
import com.ice.entity.ProductInfo;
import com.ice.service.CateGoryService;
import com.ice.service.ProductService;
import com.ice.util.ResultVOUtil;
import com.ice.vo.ProductInfoVO;
import com.ice.vo.ProductVO;
import com.ice.vo.ResultVO;

/**
 * 买家商品
 * @author 雪糕
 *
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
	  @Autowired
	  private ProductService productService;
	 
	  @Autowired
	  private CateGoryService cateGoryService;
	  @GetMapping("/list")
	  public ResultVO list(){
		  
		  /**
		   * 查询所有上架商品
		   */
   	      List<ProductInfo> productInfoList=productService.findUpAll();
   	      
   	   	  /**
   	   	   * 查询所需的类目(一次性查询
   	   	   */
   	   	  List<Integer> cateGoryTypeList =new ArrayList<>();
   	   	  
   	   	  //传统方法 
   	   	  //for( ProductInfo productInfo:productInfoList){
   	   	  // cateGoryTypeList.add(productInfo.getCategoryType()); 
   	   	  //}
   	   	  
   	   	  
   	   	  //精简方法 java8 lambda
   	       cateGoryTypeList = productInfoList.stream()
               .map(e -> e.getCategoryType())
               .collect(Collectors.toList());
   	       List<ProductCategory> productCategoryList= cateGoryService.findByCategoryTypeIn(cateGoryTypeList);
   	   	  
   	       
   	       /**
   	   	   * 数据拼装
   	   	   */
   	       
   	       List<ProductVO> productVOList =new ArrayList<>();
		     for(ProductCategory productCategory:productCategoryList){
		    	 ProductVO productVO =new ProductVO();
		    	 productVO.setCategoryType(productCategory.getCategoryType());
		    	 productVO.setCategoryName(productCategory.getCategoryName());
		    	 
		    	
		    	 
		    	 List<ProductInfoVO> productInfoVOList = new ArrayList<>();
		    	 for (ProductInfo productInfo:productInfoList){
		    		 if(productInfo.getCategoryType() ==productCategory.getCategoryType()){  //type一样才继续查询
		    			 ProductInfoVO productInfoVO =new ProductInfoVO();
		    			  BeanUtils.copyProperties(productInfo, productInfoVO);//将info的内容copy到vo里 不用一个一个set
		    	          productInfoVOList.add(productInfoVO);		 
		    		 }
		    	 }
		    	 productVO.setProductInfoVOList(productInfoVOList);
		    	 productVOList.add(productVO);
		     }
   	       
   	        
		 
	   	   
//		   ProductVO productVO =new ProductVO();
//		   ProductInfoVO productInfoVO =new ProductInfoVO();		   
//		   productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
//		   resultVO.setData(Arrays.asList(productVO));
//	   	   

	
    	   
    	  
    	   //封装的 成功的方法传集合就ok了
    	   return ResultVOUtil.success(productVOList);
       }
	  
	  /**
	   * 最外层是 resultVO  然后是 productVOList  然后是productInfoVOList  数据查询不要放到for循环里
	   */
}
