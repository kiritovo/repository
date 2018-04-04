package com.ice.controller;

import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ice.config.WechatMpConfig;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

@RestController
@RequestMapping("/wechat")
public class WechatController {
	private final Logger logger = LoggerFactory.getLogger(WechatController.class);
	@Autowired
	private WxMpService  wxMpService;
	
	
	 @GetMapping("/authorize")
	 public String authorize(@RequestParam("returnUrl") String returnUrl){
		
		//1.配置 config包下配
		//2.调用方法
		String redirectUrl =wxMpService.oauth2buildAuthorizationUrl("http://kiritovo.mynatapp.cc/sell/wechat/userInfo",WxConsts.OAUTH2_SCOPE_BASE,URLEncoder.encode(returnUrl));
	    //logger.info("[微信网页授权]获取code,result={}",redirectUrl);
	    return "redirectUrl:"+redirectUrl;   //重定向
	 }
	 @GetMapping("/userInfo")
	 public void userInfo(@RequestParam("code") String code,
	                     @RequestParam("state") String returnUrl){
		 wxMpService.oauth2getAccessToken(code);
	 }
}
