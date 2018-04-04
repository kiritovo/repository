package com.ice;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ice.entity.ProductCategory;
import com.ice.service.impl.CateGoryServiceImpl;
/**
 * service层测试成功
 * @author xt
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    
	@Autowired
	private CateGoryServiceImpl impl ;
	   

	@Test
    public void findOne() throws Exception{
//		ProductCategory category  =impl.findOne(1);
//		Assert.assertEquals(new Integer(1), category.getCategoryId());
//		System.out.println(category);
    }
    @Test
    public void findAll() throws Exception{
//    	List<ProductCategory> productCategoryList =impl.findAll();
//    	System.out.println( productCategoryList.size() );
    }
    @Test
    public void findCategoryTypeIn() throws Exception{
//    	List<ProductCategory> productCategoryList =impl.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
//    	System.out.println(productCategoryList.size());
    }
    @Test
    public void save() throws Exception{
//    	ProductCategory pro =new ProductCategory("男生最爱", 3);
//    	impl.save(pro);
    }
}
