package com.ice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix ="wechat")
public class WeChatAccountConfig {
       private  String  mpAppId ; 
        
       private  String mpAppSecret;
       
       /**
        * 商户号
        */
       private String mchId;

       /**
        * 商户密钥
        */
       private String mchKey;

       /**
        * 商户证书路径
        */
       private String keyPath;


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

		public String getMchId() {
			return mchId;
		}

		public void setMchId(String mchId) {
			this.mchId = mchId;
		}

		public String getMchKey() {
			return mchKey;
		}

		public void setMchKey(String mchKey) {
			this.mchKey = mchKey;
		}

		public String getKeyPath() {
			return keyPath;
		}

		public void setKeyPath(String keyPath) {
			this.keyPath = keyPath;
		}
		
           
           
           
}
