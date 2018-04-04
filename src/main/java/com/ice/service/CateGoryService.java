package com.ice.service;

import java.util.List;

import com.ice.entity.ProductCategory;

/**
 * 类目
 * @author Lenovo
 *
 */
public interface CateGoryService {
   //后台管理用到
   ProductCategory findOne(Integer categoryId);
   
   List<ProductCategory> findAll();
  //买家端用到   
   List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);
  //新增 ,更新
   ProductCategory save(ProductCategory productCategory);
}
