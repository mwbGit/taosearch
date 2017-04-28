package com.taosearch.dao;

import com.taosearch.model.SimpleAuthorization;
import com.taosearch.model.SysUser;

public interface LoginDao {
	SysUser doLogin(SysUser user);

	/**
	* @Title: getUserSimpleAuthorization
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param user_id
	* @param @return    设定文件
	* @return SimpleAuthorization    返回类型
	* @author jiajia 
	* @date 2017年4月6日 下午1:54:13
	* @throws
	*/
	SimpleAuthorization getUserSimpleAuthorization(String user_id);
}
