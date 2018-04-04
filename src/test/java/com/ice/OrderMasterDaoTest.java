package com.ice;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ice.dao.OrderMasterDao;
import com.ice.entity.OrderMaster;
import com.ice.entity.ProductInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {
       @Autowired
       private OrderMasterDao dao;
       
       private final String OPENID = "389416364";
       
       @Test
       public void saveTest() {
//           OrderMaster orderMaster = new OrderMaster();
//           orderMaster.setOrderId("1234567");
//           orderMaster.setBuyerName("雪糕");
//           orderMaster.setBuyerPhone("17600114213");
//           orderMaster.setBuyerAddress("丰台科技园");
//           orderMaster.setBuyerOpenid(OPENID);
//           orderMaster.setOrderAmount(new BigDecimal(2.5)); //订单金额
//
//           OrderMaster result = dao.save(orderMaster);
//           System.out.println(result);
       }
       
       @Test
       public void findByBuyerOpenid() throws Exception {
//   		PageRequest request =new PageRequest(1, 3);  //page,size
//   		Page<OrderMaster>  ordermaster = dao.findByBuyerOpenid(OPENID,request);
//   		System.out.println(ordermaster.getTotalElements());
    	
    	
       }
}
