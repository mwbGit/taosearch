/**  
* @Title: Coupon.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月17日 下午3:51:47
* @version V1.0  
*/
package com.taosearch.model;

import lombok.Getter;
import lombok.Setter;

/**
* @ClassName: Coupon
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月17日 下午3:51:47
*
*/
@Getter
@Setter
public class Coupon {

	private String coupon_id;
	private String coupon_start_time;
	private String coupon_end_time;
	private String coupon_url;
	private String activity_start_time;
	private Integer coupon_rest_num;
	private Integer coupon_get_num;
	private Integer coupon_use_num;
	
}
