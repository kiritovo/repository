package com.ice.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ice.entity.OrderMaster;

public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {
  
	   /**
	    * 某个人的订单  按照买家的openid
	    */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
   
}
