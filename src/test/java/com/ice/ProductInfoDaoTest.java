package com.ice;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ice.dao.ProductInfoDao;
import com.ice.entity.ProductInfo;
/**
 * 测试完成
 * @author Lenovo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {
   
	@Autowired
	private ProductInfoDao dao;
	
	@Test
	public void saveTest() throws Exception{
		ProductInfo pro =new ProductInfo();
		pro.setProductId("233");
		pro.setProductName("康帅傅煮面");
		pro.setProductPrice(new BigDecimal(5.2));
		pro.setProductStock(100); 
		pro.setProductDescription("炒鸡好吃");
		pro.setProductIcon("http://www.baidu.com");
		pro.setProductStatus(0);
	    pro.setCategoryType(2);
	    dao.save(pro);
		
	}
	
	
	
	@Test
    public void findByProductStatus() throws Exception{
    	List<ProductInfo> info =dao.findByProductStatus(0);
    	System.out.println( info.size()+ info.get(0).getProductName());
    }
}
