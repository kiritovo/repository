package com.ice;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ice.entity.ProductInfo;
import com.ice.service.impl.ProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
   
	@Autowired
	private ProductServiceImpl impl ;
	
	@Test
	public void findOne() throws Exception{
		ProductInfo productInfo =impl.findOne("233");
		System.out.println(productInfo.getProductName());
	}
	@Test
	public void findUpAll() throws Exception{
		  List<ProductInfo> list =impl.findUpAll();
		   System.out.println(list.size());
	}
	@Test
	public void findAll() throws Exception{
		PageRequest request =new PageRequest(0, 2);  //page,size
		Page<ProductInfo> page =impl.findAll(request);
		System.out.println(page.getTotalElements());
	}
	@Test
	public void save() throws Exception{
		         ProductInfo info =new ProductInfo("234","烤鸡",new BigDecimal(35),2,"快来吃个烤鸡吧","www.baidu.com",0,2);
                 impl.save(info);		       
	}
}
