package com.ice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ice.dao.SellerInfoDao;
import com.ice.entity.SellerInfo;
import com.ice.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {
  
	@Autowired
	private SellerInfoDao dao;
	
	
	@Override
	public SellerInfo findSellerInfoByOpenid(String openid){
		return dao.findByOpenid(openid);
	}
}
