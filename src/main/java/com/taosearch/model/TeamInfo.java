/**  
* @Title: TeamInfo.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月28日 下午9:03:30
* @version V1.0  
*/
package com.taosearch.model;

/**
* @ClassName: TeamInfo
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月28日 下午9:03:30
*
*/

public class TeamInfo {
	private String team_id;
	private String team_name;
	private String team_leader;
	private String team_leader_name;
	private String team_remark;
	private String team_state;
	private String createtime;
	private String createuser;
	private String updatetime;
	private String updateuser;

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getTeam_leader() {
		return team_leader;
	}

	public void setTeam_leader(String team_leader) {
		this.team_leader = team_leader;
	}

	public String getTeam_leader_name() {
		return team_leader_name;
	}

	public void setTeam_leader_name(String team_leader_name) {
		this.team_leader_name = team_leader_name;
	}

	public String getTeam_remark() {
		return team_remark;
	}

	public void setTeam_remark(String team_remark) {
		this.team_remark = team_remark;
	}

	public String getTeam_state() {
		return team_state;
	}

	public void setTeam_state(String team_state) {
		this.team_state = team_state;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
}