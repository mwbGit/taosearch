package com.taosearch.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taosearch.dao.UserDao;
import com.taosearch.model.MyResult;
import com.taosearch.model.SysUser;
import com.taosearch.model.TeamInfo;
import com.taosearch.service.UserService;
import com.taosearch.util.Util;


@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao dao;

	@Override
	public List<SysUser> getItemListForPage(Map<String, Object> map) {
		return dao.getItemListForPage(map);
	}

	@Override
	public int getItemCount(Map<String, Object> map) {
		return dao.getItemCount(map);
	}

	@Override
	public List<TeamInfo> getUserTeams() {
		return dao.getUserTeams();
	}

	@Override
	public int updateUserInfo(SysUser user) {
		return dao.updateUserInfo(user);
	}

	@Override
	public int updateUserDefPwd(SysUser user) {
		return dao.updateUserDefPwd(user);
	}

	@Override
	public int updateUserState(SysUser user) {
		return dao.updateUserState(user);
	}

	@Override
	public int getUserNameCount(String username) {
		return dao.getUserNameCount(username);
	}

	@Override
	public int saveUserInfo(SysUser user) {
		return dao.saveUserInfo(user);
	}

	@Override
	public List<TeamInfo> getTeamListForPage(Map<String, Object> map) {
		return dao.getTeamListForPage(map);
	}

	@Override
	public int getTeamCount(Map<String, Object> map) {
		return dao.getTeamCount(map);
	}

	@Override
	public int updateTeamState(TeamInfo team) {
		return dao.updateTeamState(team);
	}

	@Override
	public List<SysUser> getTeamLearderselectdata() {
		return dao.getTeamLearderselectdata();
	}

	@Override
	public MyResult<String> updateTeamInfo(HttpServletRequest request, TeamInfo team) {
		MyResult<String> result = new MyResult<String>();
		SysUser LoginUser = (SysUser) request.getSession().getAttribute("loginUser");
		team.setUpdateuser(LoginUser.getUser_id());
		if (team.getTeam_leader() != null && !"".equals(team.getTeam_leader())) {
			SysUser leader = dao.getLeaderByUserId(team.getTeam_leader());
			if (leader != null) {
				result.setCode(777);
				result.setMessage(leader.getUsername() + "已经是" + leader.getTeam() + "的组长");
				return result;
			}
		}
		int i = dao.updateTeamInfo(team);
		if (i > 0) {
			SysUser user = new SysUser();
			user.setUser_id(team.getTeam_leader());
			user.setTeam_id(team.getTeam_id());
			user.setUpdateuser(LoginUser.getUser_id());
			dao.updateUserInfo(user);
			result.setCode(1);
			result.setMessage("更新小组数据成功");
		} else {
			result.setCode(999);
			result.setMessage("更新小组信息失败");
		}
		return result;
	}

	@Override
	public MyResult<String> saveTeamInfo(HttpServletRequest request, TeamInfo team) {
		MyResult<String> result = new MyResult<String>();
		SysUser LoginUser = (SysUser) request.getSession().getAttribute("loginUser");
		team.setCreateuser(LoginUser.getUser_id());
		String team_id = Util.getUUID();
		team.setTeam_id(team_id);
		team.setTeam_state("1");
		if (team.getTeam_leader() != null && !"".equals(team.getTeam_leader())) {
			SysUser leader = dao.getLeaderByUserId(team.getTeam_leader());
			if (leader != null) {
				result.setCode(777);
				result.setMessage(leader.getUsername() + "已经是" + leader.getTeam() + "的组长");
				return result;
			}
		}
		int i = dao.saveTeamInfo(team);
		if (i > 0) {
			SysUser user = new SysUser();
			user.setUser_id(team.getTeam_leader());
			user.setTeam_id(team_id);
			user.setUpdateuser(LoginUser.getUser_id());
			dao.updateUserInfo(user);
			result.setCode(1);
			result.setMessage("新增小组成功");
		} else {
			result.setCode(999);
			result.setMessage("新增小组失败");
		}
		return result;
	}

	@Override
	public int getTeamNameCount(String team_name) {
		return dao.getTeamNameCount(team_name);
	}

	@Override
	public List<SysUser> getTeamUserById(String item_id) {
		return dao.getTeamUserById(item_id);
	}

}
