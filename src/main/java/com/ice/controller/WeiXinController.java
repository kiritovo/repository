package com.ice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
public class WeiXinController {
	private final Logger logger = LoggerFactory.getLogger(WeiXinController.class);
	
    @GetMapping("/auth")
	 public void  auth(@RequestParam("code") String code){
    	logger.info("进入auth方法..");
    	logger.info("code={}",code);
    	
    	
    	String url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxe469bd945bdf0c22&secret=8d2df44c2f7fc63fea550b5413c63154&code="+code+"&grant_type=authorization_code";
        RestTemplate tpl = new RestTemplate();
        String response =tpl.getForObject(url, String.class);
        logger.info("response={}",response);
    }
}
