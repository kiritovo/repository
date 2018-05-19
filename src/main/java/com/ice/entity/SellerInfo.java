package com.ice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;




@Entity
//@DynamicUpdate       //时间自动更新
public class SellerInfo {
   
	   @Id
	   private String Id;
	   
	   private String username;
	
	   private String password;
	
	   private String openid;

		
	   
	   
	   public String getSellerId() {
			return Id;
		}

		public void setSellerId(String sellerId) {
			this.Id = sellerId;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}
	    
	    
}
