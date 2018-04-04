package com.ice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ice.dto.ShopCarDTO;
import com.ice.entity.ProductInfo;

/**
 * 商品
 * @author 雪糕
 *
 */
public interface ProductService {
    
	    ProductInfo findOne(String productId);
	    /**
	     * 查询所有在架商品
	     * @return
	     */
	    List<ProductInfo> findUpAll();
	    
	    /**
	     * 管理员查询所有商品 
	     * @param pageable  分页用
	     * @return
	     */
	    Page<ProductInfo> findAll(Pageable pageable);
	    
	    
	    ProductInfo save(ProductInfo productInfo);
	    
	    // 加减库存
	    
	    void increaseStock(List<ShopCarDTO> shopcarDTO);
	     
	    void decreaseStock(List<ShopCarDTO> shopcarDTO);
}
