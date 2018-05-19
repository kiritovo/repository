package com.ice.service;

import com.ice.entity.SellerInfo;

/**
 * 卖家端
 * @author 雪糕
 *
 */
public interface SellerService {
      /**
       * 通过openid 查询卖家的信息  
       * @param openid
       * @return
       */
	  SellerInfo findSellerInfoByOpenid(String openid);
}
