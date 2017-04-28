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
@Getter
@Setter
public class SimpleAuthorization {
	private String user_id;
	private String admin;
	private String team_id;
	private String authorization;
}
