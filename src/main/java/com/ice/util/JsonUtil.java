package com.ice.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    /**
     * 统一将实体类转化成json格式
     * @param object
     * @return
     */
	public static  String toJson(Object object){
	   GsonBuilder gsonBuilder =new GsonBuilder();
	   gsonBuilder.setPrettyPrinting();
	   Gson gson= gsonBuilder.create();
	   return gson.toJson(object);
	   
	}
}
