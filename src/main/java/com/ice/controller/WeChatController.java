package com.ice.controller;

import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ice.enums.ResultEnum;
import com.ice.exception.SellException;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

@Controller
@RequestMapping("/wechat")
public class WeChatController {
	
	 @Autowired
	 private WxMpService wxMpService;
	
	 private final Logger logger = LoggerFactory.getLogger(WeChatController.class);
	
	 @GetMapping("/authorize")
	 public String authorize(@RequestParam("returnUrl") String returnUrl){
		 String url ="http://kiritovo.mynatapp.cc/sell/wechat/userInfo";
		 String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
		
		 return "redirect:" + redirectUrl;
	 }
	 
	 @GetMapping("/userInfo")
	 public String userInfo(@RequestParam("code") String code,
			              @RequestParam("state") String returnUrl) {
	       WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
	        try {
	            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
	        } catch (WxErrorException e) {
	            logger.error("【微信网页授权】{}", e);
	            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
	        }

	        String openId = wxMpOAuth2AccessToken.getOpenId();
	        
	        return "redirect:" + returnUrl + "?openid=" + openId;
	 }
}

