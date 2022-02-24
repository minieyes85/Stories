package com.minieyes.stories.user.dao;

import org.apache.ibatis.annotations.Param;

import com.minieyes.stories.user.model.User;

public interface UserDAO {

	public int insertUser(
			@Param("loginId") String loginId,
			@Param("userName") String userName,
			@Param("email") String email,
			@Param("password") String password
			);
	
	public User selectLoginId(@Param("loginId") String loginId);
	
	public User selectSignedUser(
			@Param("loginId") String loginId,
			@Param("password") String password);
}
