package com.ice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ice.dao.ProductInfoDao;
import com.ice.dto.ShopCarDTO;
import com.ice.entity.ProductInfo;
import com.ice.enums.ProductStatusEnum;
import com.ice.enums.ResultEnum;
import com.ice.exception.SellException;
import com.ice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductInfoDao dao ;
	@Override
	public ProductInfo findOne(String productId) {
		
		return dao.findOne(productId);
	}

	@Override
	public List<ProductInfo> findUpAll() {
		
		return dao.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		
		return dao.findAll(pageable);
	}

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		
		return dao.save(productInfo);
	}

	@Override
	@Transactional
	public void increaseStock(List<ShopCarDTO> shopcarDTO) {
	    for(ShopCarDTO good:shopcarDTO){
	    	ProductInfo  product =dao.findOne(good.getProductId()); //先查询
	    	if(product ==null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
	    	Integer result = product.getProductStock()+good.getProductQuantity();
	    	product.setProductStock(result);
			dao.save(product);
	    }
		
	}

	@Override
	@Transactional             //以后用redis锁机制 防止判断库存够,没抛出异常执行下面的方法    超迈问题
	public void decreaseStock(List<ShopCarDTO> shopcarDTO) {
		for(ShopCarDTO good:shopcarDTO){
			ProductInfo  product =dao.findOne(good.getProductId());
			if(product ==null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			Integer result = product.getProductStock()-good.getProductQuantity();
			
			if(result <0){
				throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
			}
			product.setProductStock(result);
			
			dao.save(product);
		}
		
	}

	
	/**
	 * 商品上架的方法
	 */
	@Override
	public ProductInfo onSale(String productId) {
		ProductInfo productInfo = dao.findOne(productId);
		 if(productInfo ==null){
			 throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		 }
		 
		 if(productInfo.getProductStatusEnum() == ProductStatusEnum.UP){
			 throw new SellException(ResultEnum.PRODUCT_STATUS_ERRPR);
		 }
		 productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
		 
		 return dao.save(productInfo);
	}
	/**
	 *商品下架的方法 
	 */
	@Override
	public ProductInfo offSale(String productId) {
		ProductInfo productInfo = dao.findOne(productId);
		 if(productInfo ==null){
			 throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		 }
		 
		 if(productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN){
			 throw new SellException(ResultEnum.PRODUCT_STATUS_ERRPR);
		 }
		 productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
		 
		 return dao.save(productInfo);
	}

}
