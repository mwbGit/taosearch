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
@Getter
@Setter
public class QueryFinancialVo {
	private String team_id;
	private String user_id;
	private String create_start_time;
	private String create_end_time;
	private String update_start_time;
	private String update_end_time;
}
