package com.ice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ice.dto.OrderDTO;
import com.ice.service.OrderService;
import com.ice.service.PayService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {
	 
	private final Logger logger = LoggerFactory.getLogger(PayServiceImplTest.class);
	
	@Autowired
	private PayService payService;
	
	@Autowired
	private OrderService odrService;
        @Test
        public void create() throws Exception{
        	OrderDTO orderDTO =odrService.findOne("1522047105913820352");
        	payService.create(orderDTO);
        }
}
