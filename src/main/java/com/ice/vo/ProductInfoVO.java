package com.ice.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商品详情
 * @author 雪糕
 *
 */

//返回前端不需要全部 所以不用entity里面的
public class ProductInfoVO {

	   @JsonProperty("id")
	    private String productId;

	    @JsonProperty("name")
	    private String productName;

	    @JsonProperty("price")
	    private BigDecimal productPrice;

	    @JsonProperty("description")
	    private String productDescription;

	    @JsonProperty("icon")
	    private String productIcon;

		
	    
	    public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public BigDecimal getProductPrice() {
			return productPrice;
		}

		public void setProductPrice(BigDecimal productPrice) {
			this.productPrice = productPrice;
		}

		public String getProductDescription() {
			return productDescription;
		}

		public void setProductDescription(String productDescription) {
			this.productDescription = productDescription;
		}

		public String getProductIcon() {
			return productIcon;
		}

		public void setProductIcon(String productIcon) {
			this.productIcon = productIcon;
		}
	    
	    
}
