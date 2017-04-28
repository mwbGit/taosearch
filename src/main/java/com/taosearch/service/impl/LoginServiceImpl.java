package com.taosearch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taosearch.dao.LoginDao;
import com.taosearch.model.SimpleAuthorization;
import com.taosearch.model.SysUser;
import com.taosearch.service.ILoginService;

@Service("LoginService")
public class LoginServiceImpl implements ILoginService {
	@Autowired
	private LoginDao dao;

	@Override
	public SysUser doLogin(SysUser user) {
		return dao.doLogin(user);
	}

	@Override
	public SimpleAuthorization getUserSimpleAuthorization(String user_id) {
		return dao.getUserSimpleAuthorization(user_id);
	}

}
