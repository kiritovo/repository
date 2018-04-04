package com.ice.util;

import java.util.Random;

public class KeyUtil {

	/**
	 * 生成唯一的主键  时间+随机数 格式
	 * @return
	 */
	public static synchronized String genUniqueKey(){
		Random random =new Random();	
		Integer number =random.nextInt(900000)+100000;  //生成六位的随机数
		
		return System.currentTimeMillis() + String.valueOf(number);
	}
}
