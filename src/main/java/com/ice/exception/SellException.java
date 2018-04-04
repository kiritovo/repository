package com.ice.exception;

import com.ice.enums.ResultEnum;

public class SellException extends RuntimeException{
 
 	
	private Integer code ;
	
	public SellException(ResultEnum resultEnum	){
		super(resultEnum.getMessage());
		
		this.code=resultEnum.getCode();
	}

	public SellException(Integer code2, String defaultMessage) {
		super(defaultMessage);
		this.code=code2;
	}
}
