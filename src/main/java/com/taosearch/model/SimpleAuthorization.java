/**  
* @Title: SimpleAuthorization.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年4月6日 下午1:44:26
* @version V1.0  
*/
package com.taosearch.model;

import lombok.Getter;
import lombok.Setter;

/**
* @ClassName: SimpleAuthorization
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年4月6日 下午1:44:26
*
*/

public class SimpleAuthorization {
	private String user_id;
	private String admin;
	private String team_id;
	private String authorization;
	private boolean showUser;
	private boolean showClaim;

	public boolean isShowClaim() {
		return showClaim;
	}

	public void setShowClaim(boolean showClaim) {
		this.showClaim = showClaim;
	}

	public boolean isShowUser() {
		return showUser;
	}

	public void setShowUser(boolean showUser) {
		this.showUser = showUser;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

}
