/**  
* @Title: Coupon.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月17日 下午3:51:47
* @version V1.0  
*/
package com.taosearch.model;

/**
* @ClassName: Coupon
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月17日 下午3:51:47
*
*/

public class Coupon {

	private String coupon_id;
	private String coupon_start_time;
	private String coupon_end_time;
	private String coupon_url;
	private String activity_start_time;
	private Integer coupon_rest_num;
	private Integer coupon_get_num;
	private Integer coupon_use_num;
	private String coupon_type_id;

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getCoupon_start_time() {
		return coupon_start_time;
	}

	public void setCoupon_start_time(String coupon_start_time) {
		this.coupon_start_time = coupon_start_time;
	}

	public String getCoupon_end_time() {
		return coupon_end_time;
	}

	public void setCoupon_end_time(String coupon_end_time) {
		this.coupon_end_time = coupon_end_time;
	}

	public String getCoupon_url() {
		return coupon_url;
	}

	public void setCoupon_url(String coupon_url) {
		this.coupon_url = coupon_url;
	}

	public String getActivity_start_time() {
		return activity_start_time;
	}

	public void setActivity_start_time(String activity_start_time) {
		this.activity_start_time = activity_start_time;
	}

	public Integer getCoupon_rest_num() {
		return coupon_rest_num;
	}

	public void setCoupon_rest_num(Integer coupon_rest_num) {
		this.coupon_rest_num = coupon_rest_num;
	}

	public Integer getCoupon_get_num() {
		return coupon_get_num;
	}

	public void setCoupon_get_num(Integer coupon_get_num) {
		this.coupon_get_num = coupon_get_num;
	}

	public Integer getCoupon_use_num() {
		return coupon_use_num;
	}

	public void setCoupon_use_num(Integer coupon_use_num) {
		this.coupon_use_num = coupon_use_num;
	}

	public String getCoupon_type_id() {
		return coupon_type_id;
	}

	public void setCoupon_type_id(String coupon_type_id) {
		this.coupon_type_id = coupon_type_id;
	}
}
