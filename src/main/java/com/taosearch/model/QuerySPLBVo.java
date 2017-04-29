/**  
* @Title: QuerySPLBVo.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年4月7日 下午1:36:44
* @version V1.0  
*/
package com.taosearch.model;

/**
* @ClassName: QuerySPLBVo
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年4月7日 下午1:36:44
*
*/

public class QuerySPLBVo {
	private String item_type;
	private String team_id;
	private String user_id;
	private String query_start_time;
	private String query_end_time;
	private String item_no;
	private String item_name;
	private String qq;
	private Integer coupon_get_num_min;
	private Integer coupon_get_num_max;

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
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

	public String getQuery_start_time() {
		return query_start_time;
	}

	public void setQuery_start_time(String query_start_time) {
		this.query_start_time = query_start_time;
	}

	public String getQuery_end_time() {
		return query_end_time;
	}

	public void setQuery_end_time(String query_end_time) {
		this.query_end_time = query_end_time;
	}

	public String getItem_no() {
		return item_no;
	}

	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public Integer getCoupon_get_num_min() {
		return coupon_get_num_min;
	}

	public void setCoupon_get_num_min(Integer coupon_get_num_min) {
		this.coupon_get_num_min = coupon_get_num_min;
	}

	public Integer getCoupon_get_num_max() {
		return coupon_get_num_max;
	}

	public void setCoupon_get_num_max(Integer coupon_get_num_max) {
		this.coupon_get_num_max = coupon_get_num_max;
	}
}
