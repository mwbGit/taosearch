/**  
 * @Title: ResultAndroid.java
 * @Package com.srit.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jiajia  
 * @date 2016年11月17日 上午1:31:21
 * @version V1.0  
 */
package com.taosearch.util;

/**
 * @ClassName: ResultAndroid
 * @Description: 用于安卓端上报信息返回
 * @author jiajia
 * @date 2016年11月17日 上午1:31:21
 *
 */
public class ResultAndroid {
	/**
	 * 返回码 1成功 0失败
	 * */
	private Integer code;

	/**
	 * 提示信息
	 * */
	private String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
