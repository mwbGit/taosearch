package com.taosearch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taosearch.model.MyResult;
import com.taosearch.model.ResultForPage;
import com.taosearch.model.SysUser;
import com.taosearch.model.TeamInfo;
import com.taosearch.service.UserService;
import com.taosearch.util.ConfigUtils;
import com.taosearch.util.MD5;
import com.taosearch.util.MD5Encoder;
import com.taosearch.util.Util;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getUserListForPage")
	@ResponseBody
	public ResultForPage<SysUser> getUserList(Integer page, Integer rows) {
		ResultForPage<SysUser> result = new ResultForPage<SysUser>();
		int pagestart = (page - 1) * rows;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagestart", pagestart);
		map.put("rows", rows);
		List<SysUser> list = userService.getItemListForPage(map);
		int count = userService.getItemCount(map);
		int totalpage = ((count - 1) / rows) + 1;
		result.setPage(page);
		result.setList(list);
		result.setRows(rows);
		result.setTotals(count);
		result.setTotalpage(totalpage);
		return result;
	}

	@RequestMapping(value = "/getTeamListForPage")
	@ResponseBody
	public ResultForPage<TeamInfo> getTeamListForPage(Integer page, Integer rows) {
		ResultForPage<TeamInfo> result = new ResultForPage<TeamInfo>();
		int pagestart = (page - 1) * rows;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagestart", pagestart);
		map.put("rows", rows);
		List<TeamInfo> list = userService.getTeamListForPage(map);
		int count = userService.getTeamCount(map);
		int totalpage = ((count - 1) / rows) + 1;
		result.setPage(page);
		result.setList(list);
		result.setRows(rows);
		result.setTotals(count);
		result.setTotalpage(totalpage);
		return result;
	}

	@RequestMapping(value = "/getUserTeams")
	@ResponseBody
	public List<TeamInfo> getUserTeams() {
		return userService.getUserTeams();
	}

	@RequestMapping(value = "/updateUserInfo")
	@ResponseBody
	public MyResult<String> updateUserInfo(HttpServletRequest request, SysUser user) {
		MyResult<String> result = new MyResult<String>();
		SysUser LoginUser = (SysUser) request.getSession().getAttribute("loginUser");
		user.setUpdateuser(LoginUser.getUser_id());
		int i = userService.updateUserInfo(user);
		if (i > 0) {
			result.setCode(1);
			result.setMessage("更新用户数据成功");
		} else {
			result.setCode(999);
			result.setMessage("更新用户信息失败");
		}
		return result;
	}

	@RequestMapping(value = "/updateTeamInfo")
	@ResponseBody
	public MyResult<String> updateTeamInfo(HttpServletRequest request, TeamInfo team) {

		MyResult<String> result = userService.updateTeamInfo(request, team);

		return result;
	}

	@RequestMapping(value = "/updateUserPassword")
	@ResponseBody
	public MyResult<String> updateUserPassword(HttpServletRequest request, SysUser user) {
		String user_password = ConfigUtils.getProp("default_pwd");
		MyResult<String> result = new MyResult<String>();
		SysUser LoginUser = (SysUser) request.getSession().getAttribute("loginUser");
		user.setUpdateuser(LoginUser.getUser_id());
		user.setPwd(MD5Encoder.encode(MD5.md5(user_password)));
		int i = userService.updateUserDefPwd(user);
		if (i > 0) {
			result.setCode(1);
			result.setMessage("重置用户密码成功");
		} else {
			result.setCode(999);
			result.setMessage("重置用户密码失败");
		}
		return result;
	}

	@RequestMapping(value = "/updteUserPwd")
	@ResponseBody
	public MyResult<String> updteUserPwd(HttpServletRequest request, String user_id, String pwd1, String pwd2) {
		MyResult<String> result = new MyResult<String>();
		SysUser user=new SysUser();
		if(pwd1==null||"".equals(pwd1)||pwd2==null||"".equals(pwd2)){
			result.setCode(777);
			result.setMessage("请输入密码");
			return result;
		}
		if(!pwd1.equals(pwd2)){
			result.setCode(444);
			result.setMessage("两次密码输入需一致");
			return result;
		}
		SysUser LoginUser = (SysUser) request.getSession().getAttribute("loginUser");
		user.setUser_id(user_id);
		user.setUpdateuser(LoginUser.getUser_id());
		user.setPwd(MD5Encoder.encode(MD5.md5(pwd1)));
		int i = userService.updateUserDefPwd(user);
		if (i > 0) {
			result.setCode(1);
			result.setMessage("用户密码修改成功");
		} else {
			result.setCode(999);
			result.setMessage("用户密码修改失败");
		}
		return result;
	}

	@RequestMapping(value = "/updateUserState")
	@ResponseBody
	public MyResult<String> updateUserState(HttpServletRequest request, SysUser user) {
		MyResult<String> result = new MyResult<String>();
		SysUser LoginUser = (SysUser) request.getSession().getAttribute("loginUser");
		user.setUpdateuser(LoginUser.getUser_id());
		int i = userService.updateUserState(user);
		if (i > 0) {
			result.setCode(1);
			result.setMessage("操作成功");
		} else {
			result.setCode(999);
			result.setMessage("操作失败");
		}
		return result;
	}

	@RequestMapping(value = "/getTeamLearderselectdata")
	@ResponseBody
	public List<SysUser> getTeamLearderselectdata() {
		List<SysUser> result = userService.getTeamLearderselectdata();
		return result;
	}
	@RequestMapping(value = "/getTeamUsers")
	@ResponseBody
	public List<SysUser> getTeamUsers(String team_id) {
		List<SysUser> result = userService.getTeamUserById(team_id);
		return result;
	}

	@RequestMapping(value = "/updateTeamState")
	@ResponseBody
	public MyResult<String> updateTeamState(HttpServletRequest request, TeamInfo team) {
		MyResult<String> result = new MyResult<String>();
		SysUser LoginUser = (SysUser) request.getSession().getAttribute("loginUser");
		team.setUpdateuser(LoginUser.getUser_id());
		int i = userService.updateTeamState(team);
		if (i > 0) {
			result.setCode(1);
			result.setMessage("操作成功");
		} else {
			result.setCode(999);
			result.setMessage("操作失败");
		}
		return result;
	}

	@RequestMapping(value = "/checkUserName")
	@ResponseBody
	public MyResult<String> checkUserName(String username) {
		MyResult<String> result = new MyResult<String>();
		if (username == null || "".equals(username) || "".equals(username.trim())) {
			result.setCode(999);
			result.setMessage("用户名不许为空");
			return result;
		}
		int i = userService.getUserNameCount(username);
		if (i > 0) {
			result.setCode(777);
			result.setMessage("用户名已被使用");
		} else {
			result.setCode(1);
			result.setMessage("用户名可用");
		}
		return result;
	}

	@RequestMapping(value = "/checkTeamName")
	@ResponseBody
	public MyResult<String> checkTeamName(String team_name) {
		MyResult<String> result = new MyResult<String>();
		if (team_name == null || "".equals(team_name) || "".equals(team_name.trim())) {
			result.setCode(999);
			result.setMessage("小组户名不许为空");
			return result;
		}
		int i = userService.getTeamNameCount(team_name);
		if (i > 0) {
			result.setCode(777);
			result.setMessage("小组户名已被使用");
		} else {
			result.setCode(1);
			result.setMessage("小组户名可用");
		}
		return result;
	}

	@RequestMapping(value = "/saveUserInfo")
	@ResponseBody
	public MyResult<String> saveUserInfo(HttpServletRequest request, SysUser user) {
		String user_password = ConfigUtils.getProp("default_pwd");
		MyResult<String> result = new MyResult<String>();
		SysUser LoginUser = (SysUser) request.getSession().getAttribute("loginUser");
		user.setCreateuser(LoginUser.getUser_id());
		user.setPwd(MD5Encoder.encode(MD5.md5(user_password)));
		user.setUser_id(Util.getUUID());
		user.setState("1");
		int i = userService.saveUserInfo(user);
		if (i > 0) {
			result.setCode(1);
			result.setMessage("新增用户成功");
		} else {
			result.setCode(999);
			result.setMessage("新增用户失败");
		}
		return result;
	}

	@RequestMapping(value = "/saveTeamInfo")
	@ResponseBody
	public MyResult<String> saveTeamInfo(HttpServletRequest request, TeamInfo team) {

		MyResult<String> result = userService.saveTeamInfo(request, team);

		return result;
	}
}
