/**  
* @Title: ItemAttachment.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月22日 下午5:28:03
* @version V1.0  
*/
package com.taosearch.model;

/**
* @ClassName: ItemAttachment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月22日 下午5:28:03
*
*/

public class ItemAttachment {

	private Integer attachment_id;
	private String item_id;
	private String attachment_value;
	private String attachment_user_name;
	private String attachment_createtime;

	public Integer getAttachment_id() {
		return attachment_id;
	}

	public void setAttachment_id(Integer attachment_id) {
		this.attachment_id = attachment_id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getAttachment_value() {
		return attachment_value;
	}

	public void setAttachment_value(String attachment_value) {
		this.attachment_value = attachment_value;
	}

	public String getAttachment_user_name() {
		return attachment_user_name;
	}

	public void setAttachment_user_name(String attachment_user_name) {
		this.attachment_user_name = attachment_user_name;
	}

	public String getAttachment_createtime() {
		return attachment_createtime;
	}

	public void setAttachment_createtime(String attachment_createtime) {
		this.attachment_createtime = attachment_createtime;
	}
}
