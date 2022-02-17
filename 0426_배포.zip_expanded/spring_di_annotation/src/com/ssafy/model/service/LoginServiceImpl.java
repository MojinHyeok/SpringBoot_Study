package com.ssafy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.MemberDto;
import com.ssafy.model.dao.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Override
	public MemberDto login(String userid, String userpwd) throws Exception {
		if(userid == null || userpwd == null)
			return null;
		return loginDao.login(userid, userpwd);
	}

}
