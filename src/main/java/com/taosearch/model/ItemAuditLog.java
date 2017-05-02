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

public class ItemAuditLog {
	private Integer log_id;
	private String item_id;
	private String before_audit_status;
	private String after_audit_status;
	private String auditor;
	private String audit_time;
	private String audit_remarks;

	public Integer getLog_id() {
		return log_id;
	}

	public void setLog_id(Integer log_id) {
		this.log_id = log_id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getBefore_audit_status() {
		return before_audit_status;
	}

	public void setBefore_audit_status(String before_audit_status) {
		this.before_audit_status = before_audit_status;
	}

	public String getAfter_audit_status() {
		return after_audit_status;
	}

	public void setAfter_audit_status(String after_audit_status) {
		this.after_audit_status = after_audit_status;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getAudit_time() {
		return audit_time;
	}

	public void setAudit_time(String audit_time) {
		this.audit_time = audit_time;
	}

	public String getAudit_remarks() {
		return audit_remarks;
	}

	public void setAudit_remarks(String audit_remarks) {
		this.audit_remarks = audit_remarks;
	}
}
