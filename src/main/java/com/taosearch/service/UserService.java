package com.taosearch.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.taosearch.model.MyResult;
import com.taosearch.model.SysUser;
import com.taosearch.model.TeamInfo;

/**
 * 
* @ClassName: UserService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月23日 下午4:25:54
*
 */
public interface UserService {

	/**
	* @Title: getItemListForPage
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param map
	* @param @return    设定文件
	* @return List<SysUser>    返回类型
	* @author jiajia 
	* @date 2017年3月27日 下午8:38:00
	* @throws
	*/
	List<SysUser> getItemListForPage(Map<String, Object> map);

	/**
	* @Title: getItemCount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param map
	* @param @return    设定文件
	* @return int    返回类型
	* @author jiajia 
	* @date 2017年3月27日 下午8:38:07
	* @throws
	*/
	int getItemCount(Map<String, Object> map);

	/**
	* @Title: getUserTeams
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return List<TeamInfo>    返回类型
	* @author jiajia 
	* @date 2017年3月28日 下午9:08:39
	* @throws
	*/
	List<TeamInfo> getUserTeams();

	/**
	* @Title: updateUserInfo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param user
	* @param @return    设定文件
	* @return int    返回类型
	* @author jiajia 
	* @date 2017年3月29日 上午10:20:43
	* @throws
	*/
	int updateUserInfo(SysUser user);

	/**
	* @Title: updateUserDefPwd
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param user
	* @param @return    设定文件
	* @return int    返回类型
	* @author jiajia 
	* @date 2017年3月29日 上午11:13:37
	* @throws
	*/
	int updateUserDefPwd(SysUser user);

	/**
	* @Title: updateUserState
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param user
	* @param @return    设定文件
	* @return int    返回类型
	* @author jiajia 
	* @date 2017年3月29日 下午2:00:11
	* @throws
	*/
	int updateUserState(SysUser user);

	/**
	* @Title: getUserNameCount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param username
	* @param @return    设定文件
	* @return int    返回类型
	* @author jiajia 
	* @date 2017年3月29日 下午3:21:08
	* @throws
	*/
	int getUserNameCount(String username);

	/**
	* @Title: saveUserInfo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param user
	* @param @return    设定文件
	* @return int    返回类型
	* @author jiajia 
	* @date 2017年3月29日 下午3:58:11
	* @throws
	*/
	int saveUserInfo(SysUser user);

	/**
	* @Title: getTeamListForPage
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param map
	* @param @return    设定文件
	* @return List<TeamInfo>    返回类型
	* @author jiajia 
	* @date 2017年3月31日 上午9:35:02
	* @throws
	*/
	List<TeamInfo> getTeamListForPage(Map<String, Object> map);

	/**
	* @Title: getTeamCount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param map
	* @param @return    设定文件
	* @return int    返回类型
	* @author jiajia 
	* @date 2017年3月31日 上午9:35:06
	* @throws
	*/
	int getTeamCount(Map<String, Object> map);

	/**
	* @Title: updateTeamState
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param team
	* @param @return    设定文件
	* @return int    返回类型
	* @author jiajia 
	* @date 2017年3月31日 上午10:10:44
	* @throws
	*/
	int updateTeamState(TeamInfo team);

	/**
	* @Title: getTeamLearderselectdata
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return List<SysUser>    返回类型
	* @author jiajia 
	* @date 2017年3月31日 上午10:51:35
	* @throws
	*/
	List<SysUser> getTeamLearderselectdata();

	/**
	 * @param request 
	* @Title: updateTeamInfo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param team
	* @param @return    设定文件
	* @return int    返回类型
	* @author jiajia 
	* @date 2017年3月31日 上午11:08:59
	* @throws
	*/
	MyResult<String> updateTeamInfo(HttpServletRequest request, TeamInfo team);

	/**
	 * 
	* @Title: saveTeamInfo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param request
	* @param @param team
	* @param @return    设定文件
	* @return MyResult<String>    返回类型
	* @author jiajia 
	* @date 2017年3月31日 下午6:43:05
	* @throws
	 */
	MyResult<String> saveTeamInfo(HttpServletRequest request, TeamInfo team);

	/**
	* @Title: getTeamNameCount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param team_name
	* @param @return    设定文件
	* @return int    返回类型
	* @author jiajia 
	* @date 2017年3月31日 下午4:23:32
	* @throws
	*/
	int getTeamNameCount(String team_name);

	/**
	* @Title: getTeamUserById
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param item_id
	* @param @return    设定文件
	* @return List<SysUser>    返回类型
	* @author jiajia 
	* @date 2017年4月7日 上午4:15:08
	* @throws
	*/
	List<SysUser> getTeamUserById(String item_id);


}
