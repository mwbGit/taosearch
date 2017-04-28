/**  
* @Title: ILoginService.java
* @Package com.taosearch.service
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月8日 上午11:13:30
* @version V1.0  
*/
package com.taosearch.service;

import com.taosearch.model.SimpleAuthorization;
import com.taosearch.model.SysUser;

/**
* @ClassName: ILoginService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月8日 上午11:13:30
*
*/
public interface ILoginService {

	/**
	* @Title: doLogin
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param user
	* @param @return    设定文件
	* @return SysUser    返回类型
	* @author jiajia 
	* @date 2017年3月8日 上午11:23:33
	* @throws
	*/
	SysUser doLogin(SysUser user);

	SimpleAuthorization getUserSimpleAuthorization(String user_id);
}
