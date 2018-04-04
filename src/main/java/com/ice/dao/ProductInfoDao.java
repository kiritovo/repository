package com.ice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ice.entity.ProductInfo;

public interface ProductInfoDao extends JpaRepository<ProductInfo, String> {
	/**
	 * 查询上架的商品
	 * @param productStatus
	 * @return
	 */
	List<ProductInfo>  findByProductStatus(Integer productStatus);

}
