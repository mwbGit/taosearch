/**  
* @Title: TeamInfo.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月28日 下午9:03:30
* @version V1.0  
*/
package com.taosearch.model;

import lombok.Getter;
import lombok.Setter;

/**
* @ClassName: TeamInfo
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月28日 下午9:03:30
*
*/
@Getter
@Setter
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
}