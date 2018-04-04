package com.ice;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ice.dao.OrderDetailDao;
import com.ice.entity.OrderDetail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
   
	 @Autowired
	 private OrderDetailDao dao;
	 
	 @Test
	 public  void saveTest(){
//		 OrderDetail entity =new OrderDetail();
//		 entity.setDetailId("0000001");
//		 entity.setOrderId("0000001");
//		 entity.setProductIcon("http:xxx.jpg");
//		 entity.setProductId("001");
//		 entity.setProductName("康帅傅煮面");
//		 entity.setProductPrice(new BigDecimal(5.2));
//		 entity.setProductQuantity(2);
//		 OrderDetail orderdetial =dao.save(entity);
//		 System.out.println(orderdetial);
	 }
	 
	 
	 @Test 
	 public void findByOrderId() throws Exception{
		 List<OrderDetail> list = dao.findByOrderId("0000001");
		 System.out.println(list.size());
	 }
}
