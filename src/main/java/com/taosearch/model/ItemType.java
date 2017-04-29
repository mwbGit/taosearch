/**  
* @Title: ItemType.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月14日 下午2:42:06
* @version V1.0  
*/
package com.taosearch.model;

/**
* @ClassName: ItemType
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月14日 下午2:42:06
*
*/

public class ItemType {
	private String type_id;
	private String type_name;

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
}
