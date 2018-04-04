package com.ice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * 微信公众账号的配置
 * @author 雪糕
 *
 */
@Component
public class WechatMpConfig {
 
	@Autowired
	private WechatAccountConfig accountConfig;
	
	@Bean
	public WxMpService wxMpService(){
		WxMpService wxMpService =new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
		return wxMpService;
	}
	
	@Bean
	public WxMpConfigStorage wxMpConfigStorage(){
		WxMpInMemoryConfigStorage wxMpConfigStorage =new  WxMpInMemoryConfigStorage(); //基于内存的实现类
		wxMpConfigStorage.setAppId(accountConfig.getMpAppId());
		wxMpConfigStorage.setSecret(accountConfig.getMpAppSecret());
		return wxMpConfigStorage;
		
		
	}
}
