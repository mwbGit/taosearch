/**  
* @Title: QueryFinancialVo.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年4月8日 下午9:06:15
* @version V1.0  
*/
package com.taosearch.model;

import lombok.Getter;
import lombok.Setter;

/**
* @ClassName: QueryFinancialVo
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年4月8日 下午9:06:15
*
*/

public class QueryFinancialVo {
	private String team_id;
	private String user_id;
	private String create_start_time;
	private String create_end_time;
	private String update_start_time;
	private String update_end_time;
	private String ssje;
	private String totle;
	private String daynum;
	private String order;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSsje() {
		return ssje;
	}

	public void setSsje(String ssje) {
		this.ssje = ssje;
	}

	public String getTotle() {
		return totle;
	}

	public void setTotle(String totle) {
		this.totle = totle;
	}

	public String getDaynum() {
		return daynum;
	}

	public void setDaynum(String daynum) {
		this.daynum = daynum;
	}

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCreate_start_time() {
		return create_start_time;
	}

	public void setCreate_start_time(String create_start_time) {
		this.create_start_time = create_start_time;
	}

	public String getCreate_end_time() {
		return create_end_time;
	}

	public void setCreate_end_time(String create_end_time) {
		this.create_end_time = create_end_time;
	}

	public String getUpdate_start_time() {
		return update_start_time;
	}

	public void setUpdate_start_time(String update_start_time) {
		this.update_start_time = update_start_time;
	}

	public String getUpdate_end_time() {
		return update_end_time;
	}

	public void setUpdate_end_time(String update_end_time) {
		this.update_end_time = update_end_time;
	}
}
