/**  
* @Title: ItemAttachment.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月22日 下午5:28:03
* @version V1.0  
*/
package com.taosearch.model;

import lombok.Getter;
import lombok.Setter;

/**
* @ClassName: ItemAttachment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月22日 下午5:28:03
*
*/
@Getter
@Setter
public class ItemAttachment {

	private Integer attachment_id;
	private String item_id;
	private String attachment_value;
	private String attachment_user_name;
	private String attachment_createtime;
}
