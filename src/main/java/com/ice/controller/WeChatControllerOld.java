//package com.ice.controller;
//
//import java.net.URLEncoder;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ice.enums.ResultEnum;
//import com.ice.exception.SellException;
//
//import me.chanjar.weixin.common.api.WxConsts;
//import me.chanjar.weixin.common.exception.WxErrorException;
//import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
//import me.chanjar.weixin.mp.api.WxMpService;
//import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
//import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
//
//@RestController
//@RequestMapping("/wechat")
//public class WeChatControllerOld {
//	private final Logger logger = LoggerFactory.getLogger(WeChatControllerOld.class);
//	
////	@Autowired
////	private WxMpService wxMpService;
//	
//
//	@GetMapping("/authorize")
//	public String authorize(@RequestParam("returnUrl") String returnUrl){
//		WxMpService wxMpService =new 	WxMpServiceImpl();
//		
//		WxMpInMemoryConfigStorage wxMpConfigStorage=new WxMpInMemoryConfigStorage();
//		wxMpConfigStorage.setAppId("wxe469bd945bdf0c22");
//		wxMpConfigStorage.setSecret("8d2df44c2f7fc63fea550b5413c63154");
//		
//		wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
//		String url ="http://kiritovo.mynatapp.cc/sell/wechat/userInfo";
//		String  redirectUrl =wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO,URLEncoder.encode(returnUrl));
//		return "redirect:" +redirectUrl;
//		
//		
//	}
//    @GetMapping("userInfo")	
//	public String  userInfo(@RequestParam("code") String code,
//			             @RequestParam("state") String returnUrl){
//    	WxMpService wxMpService =new 	WxMpServiceImpl();
//    	WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
//        try {
//            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
//        } catch (WxErrorException e) {
//            logger.error("【微信网页授权】{}", e);
//            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
//        }
//
//        String openId = wxMpOAuth2AccessToken.getOpenId();
//
//        return "redirect:" + returnUrl + "?openid=" + openId;
//	}
//}
