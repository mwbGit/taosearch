package com.taosearch.dao;

import java.util.List;
import java.util.Map;

import com.taosearch.model.SysUser;
import com.taosearch.model.TeamInfo;

/**
 * 
* @ClassName: UserDao
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月23日 下午4:26:06
*
 */
public interface UserDao {

	/**
	* @Title: getItemListForPage
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param map
	* @param @return    设定文件
	* @return List<SysUser>    返回类型
	* @author jiajia 
	* @date 2017年3月27日 下午8:39:14
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
	* @date 2017年3月27日 下午8:39:18
	* @throws
	*/
	int getItemCount(Map<String, Object> map);

	/**
	* @Title: getUserTeams
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return List<TeamInfo>    返回类型
	* @author jiajia 
	* @date 2017年3月28日 下午9:09:10
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
	* @date 2017年3月29日 上午10:21:17
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
	* @date 2017年3月29日 上午11:13:59
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
	* @date 2017年3月29日 下午2:00:37
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
	* @date 2017年3月29日 下午3:21:34
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
	* @date 2017年3月29日 下午3:58:44
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
	* @date 2017年3月31日 上午9:36:44
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
	* @date 2017年3月31日 上午9:36:50
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
	* @date 2017年3月31日 上午10:11:17
	* @throws
	*/
	int updateTeamState(TeamInfo team);

	/**
	* @Title: getTeamLearderselectdata
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return List<SysUser>    返回类型
	* @author jiajia 
	* @date 2017年3月31日 上午10:52:03
	* @throws
	*/
	List<SysUser> getTeamLearderselectdata();

	/**
	* @Title: updateTeamInfo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param team
	* @param @return    设定文件
	* @return int    返回类型
	* @author jiajia 
	* @date 2017年3月31日 上午11:09:31
	* @throws
	*/
	int updateTeamInfo(TeamInfo team);

	/**
	* @Title: saveTeamInfo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param team
	* @param @return    设定文件
	* @return int    返回类型
	* @author jiajia 
	* @date 2017年3月31日 下午3:35:38
	* @throws
	*/
	int saveTeamInfo(TeamInfo team);

	/**
	* @Title: getTeamNameCount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param team_name
	* @param @return    设定文件
	* @return int    返回类型
	* @author jiajia 
	* @date 2017年3月31日 下午4:24:02
	* @throws
	*/
	int getTeamNameCount(String team_name);

	/**
	* @Title: getLeaderByUserId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param team_leader
	* @param @return    设定文件
	* @return SysUser    返回类型
	* @author jiajia 
	* @date 2017年4月2日 上午11:12:37
	* @throws
	*/
	SysUser getLeaderByUserId(String team_leader);

	/**
	* @Title: getTeamUserById
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param item_id
	* @param @return    设定文件
	* @return List<SysUser>    返回类型
	* @author jiajia 
	* @date 2017年4月7日 上午4:17:19
	* @throws
	*/
	List<SysUser> getTeamUserById(String team_id);
}
