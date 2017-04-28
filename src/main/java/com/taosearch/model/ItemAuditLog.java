/**  
* @Title: ItemAuditLog.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月22日 下午5:34:57
* @version V1.0  
*/
package com.taosearch.model;

import lombok.Getter;
import lombok.Setter;

/**
* @ClassName: ItemAuditLog
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月22日 下午5:34:57
*
*/
@Getter
@Setter
public class ItemAuditLog {
	private Integer log_id;
	private String item_id;
	private String before_audit_status;
	private String after_audit_status;
	private String auditor;
	private String audit_time;
	private String audit_remarks;
}
