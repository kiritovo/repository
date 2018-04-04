package com.ice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信账号相关的配置
 * @author 雪糕
 *
 */
@Component
@ConfigurationProperties(prefix="wechat")
public class WechatAccountConfig {
     
	 private String mpAppId;
	 
	 private String mpAppSecret;


	 
	 public String getMpAppId() {
		return mpAppId;
	}

	public void setMpAppId(String mpAppId) {
		this.mpAppId = mpAppId;
	}

	public String getMpAppSecret() {
		return mpAppSecret;
	}

	public void setMpAppSecret(String mpAppSecret) {
		this.mpAppSecret = mpAppSecret;
	}
	 
	 
}
