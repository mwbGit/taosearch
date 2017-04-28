/**  
 * @Title: ListUtil.java
 * @Package com.srit.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jiajia  
 * @date 2016年11月13日 下午1:32:53
 * @version V1.0  
 */
package com.taosearch.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

/**
 * @ClassName: ListUtil
 * @Description: 集合帮助类
 * @author jiajia
 * @date 2016年11月13日 下午1:32:53
 *
 */
public class ListUtil<T> {
	/**
	 * 
	 * @Title: queryForPage
	 * @Description: 用于程序分页 大集合转小集合
	 * @param @param pagestart
	 * @param @param rows
	 * @param @param list
	 * @param @return 设定文件
	 * @return List<T> 返回类型
	 * @author jiajia
	 * @date 2016年11月13日 下午1:39:24
	 * @throws
	 */
	public List<T> queryForPage(int pagestart, int rows, List<T> list) {
		List<T> resultList = new ArrayList<T>();
		int length = list.size();
		int total = pagestart + rows;
		int end = length > total ? total : length;
		for (int i = pagestart; i < end; i++) {
			resultList.add(list.get(i));
		}
		return resultList;
	}
}
