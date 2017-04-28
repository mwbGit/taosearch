/**  
* @Title: ResultForPage.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月20日 下午2:17:00
* @version V1.0  
*/
package com.taosearch.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
* @ClassName: ResultForPage
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月20日 下午2:17:00
*
*/
@Getter
@Setter
public class ResultForPage<T> {
	private Integer page;// 当前页
	private Integer rows;// 每页的条数
	private Integer totalpage;// 总页数
	private Integer totals;// 总条数
	private List<T> list;//
}
