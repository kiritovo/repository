package com.ice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ice.entity.SellerInfo;

public interface SellerInfoDao extends JpaRepository<SellerInfo, String> {
  
	  SellerInfo findByOpenid(String openid);
	  
	  
}
