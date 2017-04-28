/**  
* @Title: ResultBase.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月16日 下午2:10:40
* @version V1.0  
*/
package com.taosearch.model;

import lombok.Getter;
import lombok.Setter;

/**
* @ClassName: ResultBase
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月16日 下午2:10:40
*
*/
@Getter
@Setter
public  class MyResult<T> {
	/**
	 * 返回码
	 * */
	private Integer code;

	/**
	 * 数据信息
	 */
	private T data;

	/**
	 * 提示信息
	 * */
	private String message;
}
