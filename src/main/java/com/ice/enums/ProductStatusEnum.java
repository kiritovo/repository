package com.ice.enums;
/**
 * 
 * 商品状态 枚举 类似于用一个接口把数据都存进去 放常量
 * @author 雪糕
 *
 */
public enum ProductStatusEnum implements CodeEnum {
    UP(0,"在架"),
	DOWN(1,"下架")
	;
	private Integer code;
	
	private String message; 
	
	
	private ProductStatusEnum(Integer code, String message) {
		this.code =code;
		this.message=message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	

	
	
	
}
