package com.minieyes.stories.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minieyes.stories.common.EncryptUtils;
import com.minieyes.stories.user.dao.UserDAO;
import com.minieyes.stories.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	UserDAO userDAO;
	
	public int addUser(String loginId, String userName, String email, String password) {
		
		String encPassword = EncryptUtils.md5(password);
		
		return userDAO.insertUser(loginId, userName, email, encPassword);
	}
	
	public boolean findUser(String loginId) {
		
		if(userDAO.selectLoginId(loginId) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public User getUser(String loginId, String password) {
		
		String encPassword = EncryptUtils.md5(password);
		
		return userDAO.selectSignedUser(loginId, encPassword);
	}
}
