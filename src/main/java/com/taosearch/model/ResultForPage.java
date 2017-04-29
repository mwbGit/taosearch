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

/**
* @ClassName: ResultForPage
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月20日 下午2:17:00
*
*/

public class ResultForPage<T> {
	private Integer page;// 当前页
	private Integer rows;// 每页的条数
	private Integer totalpage;// 总页数
	private Integer totals;// 总条数
	private List<T> list;//

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(Integer totalpage) {
		this.totalpage = totalpage;
	}

	public Integer getTotals() {
		return totals;
	}

	public void setTotals(Integer totals) {
		this.totals = totals;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
