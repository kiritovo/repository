package com.ice.util;

import com.ice.enums.CodeEnum;

public class EnumUtil {
    /**
     * 通过code 枚举类  将数据库中的数字转成汉字
     * @param code
     * @param enumClass
     * @return
     */
	 public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
		 for(T each:enumClass.getEnumConstants()){
			 if(code.equals(each.getCode())){
				 return each;
			 }
		 }
		  return null;
	 } 
}
