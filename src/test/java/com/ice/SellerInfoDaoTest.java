package com.ice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ice.dao.SellerInfoDao;
import com.ice.entity.SellerInfo;
import com.ice.util.KeyUtil;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest	
public class SellerInfoDaoTest {
   
	
	@Autowired
	private SellerInfoDao dao;
	
	  @Test 
	  public void save(){
		  SellerInfo sellerInfo =new  SellerInfo();
		  sellerInfo.setSellerId(KeyUtil.genUniqueKey());  //生成唯一的主键 时间+随机数 格式
		  sellerInfo.setOpenid("abc");
		  sellerInfo.setUsername("admin");
		  sellerInfo.setPassword("admin");
		  SellerInfo result = dao.save(sellerInfo);
		  System.out.println(result);
		  Assert.assertNotNull(result);
	  }
	
	  @Test
	  public void findByOpenid() throws Exception{
		  SellerInfo result =dao.findByOpenid("abc");
		  Assert.assertEquals("abc", result.getOpenid());
	  }
}
