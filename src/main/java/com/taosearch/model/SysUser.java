/**  
* @Title: SysUser.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月8日 上午10:07:17
* @version V1.0  
*/
package com.taosearch.model;

/**
* @ClassName: SysUser
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月8日 上午10:07:17
*
*/

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
	private String admin;//1是管理员0不是管理员4审核员5财务

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
}
