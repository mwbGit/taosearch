/**  
* @Title: QuerySPLBVo.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年4月7日 下午1:36:44
* @version V1.0  
*/
package com.taosearch.model;

import lombok.Getter;
import lombok.Setter;

/**
* @ClassName: QuerySPLBVo
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年4月7日 下午1:36:44
*
*/
@Getter
@Setter
public class QuerySPLBVo {
	private String item_type;
	private String team_id;
	private String user_id;
	private String query_start_time;
	private String query_end_time;
	private String item_no;
	private String item_name;
}
