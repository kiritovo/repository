package com.ice;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ice.dao.ProductCategoryDao;
import com.ice.entity.ProductCategory;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao dao ;
    
    
    @Test
    public void findOneTest(){
    	ProductCategory pro = dao.findOne(1);
    	System.out.println(pro.toString());
    }
    
    @Test 
    public void saveTest(){
    	ProductCategory pro =new ProductCategory();
    	//pro.setCategoryId(2);
    	//pro.setCategoryName("男生最爱");
    	//有主键就可以更新了
    	
    	//pro.setCategoryName("女生最爱");
    	//pro.setCategoryType(3);
    	//dao.save(pro);
    }
    
    
    @Test
    public void changeTest(){
    	ProductCategory pro =dao.findOne(2);
    	pro.setCategoryType(8);
    	ProductCategory  result = dao.save(pro);
    	Assert.assertNotNull(result);
    	
    }
    
    @Test
    public void findByCategoryTypeInTest(){
    	List<Integer> list = Arrays.asList(2,3,8);
    	List<ProductCategory> categorylist =dao.findByCategoryTypeIn(list);
    	Assert.assertNotSame(0, categorylist.size());
    }
}
