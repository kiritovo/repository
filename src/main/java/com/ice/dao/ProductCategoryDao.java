package com.ice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ice.entity.ProductCategory;

//对象   主键是integer类型
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {
    /**
     * 查出指定类型的餐 
     * @param CategoryTypeList
     * @return
     */
	List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryTypeList); 
}
