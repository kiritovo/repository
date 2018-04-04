package com.ice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ice.dao.ProductCategoryDao;
import com.ice.entity.ProductCategory;
import com.ice.service.CateGoryService;

@Service
public class CateGoryServiceImpl implements CateGoryService {
    
	@Autowired
	private ProductCategoryDao dao;
	 
	@Override
	public ProductCategory findOne(Integer categoryId) {
		
		return dao.findOne(categoryId);
	}

	@Override
	public List<ProductCategory> findAll() {
		
		return dao.findAll();
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType) {
		
		return dao.findByCategoryTypeIn(categoryType);
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		
		return dao.save(productCategory);
	}

}
