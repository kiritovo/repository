package com.ice.dto;
/**
 * 购物车
 * @author 雪糕
 *
 */
public class ShopCarDTO {
	  //商品id
	  private String productId;
	  //数量
	  private Integer productQuantity;
	  
	  

		public ShopCarDTO(String productId, Integer productQuantity) {
		super();
		this.productId = productId;
		this.productQuantity = productQuantity;
		}

		public String getProductId() {
			return productId;
		}
	
		public void setProductId(String productId) {
			this.productId = productId;
		}
	
		public Integer getProductQuantity() {
			return productQuantity;
		}

		public void setProductQuantity(Integer productQuantity) {
			this.productQuantity = productQuantity;
		}
      
      
} 
