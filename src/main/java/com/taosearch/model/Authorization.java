/**  
* @Title: Authorization.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月23日 下午4:35:24
* @version V1.0  
*/
package com.taosearch.model;

/**
* @ClassName: Authorization
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月23日 下午4:35:24
*
*/
public class Authorization {
	private Integer id;
	private String authorization;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
}
