package com.ice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ice.entity.SellerInfo;
import com.ice.service.impl.SellerServiceImpl;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceTest {

	private final Logger logger = LoggerFactory.getLogger(SellerServiceTest.class);
	
	private static final String openid ="abc";
	
	@Autowired
	private SellerServiceImpl service;
	
	@Test
	public void findSellerInfoByOpenid() throws Exception{
		SellerInfo result =service.findSellerInfoByOpenid(openid);
		System.out.println(result);
		Assert.assertEquals(openid,result.getOpenid());
	}
	
}
