package com.ssafy.model.service;

import com.ssafy.model.MemberDto;
import com.ssafy.model.dao.LoginDao;

public class LoginServiceImpl implements LoginService {

	private LoginDao loginDao;
	
	public LoginServiceImpl(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public MemberDto login(String userid, String userpwd) throws Exception {
		if(userid == null || userpwd == null)
			return null;
		return loginDao.login(userid, userpwd);
	}

}
