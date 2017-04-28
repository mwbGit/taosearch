package com.taosearch.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taosearch.model.SimpleAuthorization;
import com.taosearch.model.SysUser;
import com.taosearch.service.ILoginService;
import com.taosearch.util.MD5;
import com.taosearch.util.MD5Encoder;
import com.taosearch.util.Util;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@Autowired
	private ILoginService LoginService;

	@RequestMapping(value = "/doLogin")
	@ResponseBody
	public boolean login(HttpServletRequest request) {
		boolean flag = false;
		String username = request.getParameter("login_id");
		String pwd = request.getParameter("user_password");
		System.out.println(pwd);
		username=Util.checkPOSTParameter(username);
		System.out.println(username);
		if (null != pwd && !"".equals(pwd) && null != username && !"".equals(username)) {
			pwd = MD5Encoder.encode(MD5.md5(pwd));
			System.out.println(pwd);
			SysUser user = new SysUser();
			user.setUsername(username);
			user.setPwd(pwd);
			user.setState("1");
			user = LoginService.doLogin(user);
			if (user != null) {
				request.getSession().setAttribute("loginUser", user);
				SimpleAuthorization sa=LoginService.getUserSimpleAuthorization(user.getUser_id());
				String authorization="1";//普通人员
				if("1".equals(sa.getAdmin())){
					authorization="3";//管理员
				}else if ("0".equals(sa.getAdmin())&&sa.getTeam_id()!=null){
					authorization="2";//小组长
				}
				sa.setAuthorization(authorization);
				request.getSession().setAttribute("userAuthorization", sa);
				flag = true;
			}
		}
		return flag;
	}

}
