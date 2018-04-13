package com.ice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * 公众号配置
 * @author 雪糕
 *
 */
@Component
public class WeChatMPConfig {
	//token
           
	@Autowired
	private WeChatAccountConfig Account;
	
	@Bean
	public	WxMpService  wxMpService(){
		WxMpService wxMpService =new 	WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
		return wxMpService;
	}
	
	@Bean
	public WxMpConfigStorage wxMpConfigStorage(){
		WxMpInMemoryConfigStorage wxMpConfigStorage=new WxMpInMemoryConfigStorage();
		wxMpConfigStorage.setAppId(Account.getMpAppId());
		wxMpConfigStorage.setSecret(Account.getMpAppSecret());
		return wxMpConfigStorage;
	}
}
