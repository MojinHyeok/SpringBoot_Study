package com.backend.study.screen.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao  extends JpaRepository<UserDto, Integer>{
	
	UserDto findByuserName(String userName);
}
