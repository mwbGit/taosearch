/**  
* @Title: SysUser.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月8日 上午10:07:17
* @version V1.0  
*/
package com.taosearch.model;

import lombok.Getter;
import lombok.Setter;

/**
* @ClassName: SysUser
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月8日 上午10:07:17
*
*/
@Getter
@Setter
public class SysUser {
	private String user_id;
	private String username;
	private String pwd;
	private String createtime;
	private String updatetime;
	private String tel;
	private String state;// 1启用 0停用
	private String team;
	private String team_id;
	private String createuser;
	private String updateuser;
	private String admin;//1是管理员0不是管理员
}
