package com.ice.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * (商品包含类目
 * @author 雪糕
 *
 */
public class ProductVO {
     
    @JsonProperty("name")   //返回给前短
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

	
    
    
    public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public List<ProductInfoVO> getProductInfoVOList() {
		return productInfoVOList;
	}

	public void setProductInfoVOList(List<ProductInfoVO> productInfoVOList) {
		this.productInfoVOList = productInfoVOList;
	}
    
    
}
