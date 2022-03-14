package com.minieyes.stories.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minieyes.stories.bbs.model.BBS;
import com.minieyes.stories.common.EncryptUtils;
import com.minieyes.stories.main.bo.MainBO;
import com.minieyes.stories.user.dao.UserDAO;
import com.minieyes.stories.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	MainBO mainBO;
	
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
	
	public User getSignedUser(int id) {
		return userDAO.selectById(id);
	}
	
	public boolean checkPassword(int id, String password) {
		
		String encPassword = EncryptUtils.md5(password);
		
		User user = userDAO.selectById(id);
		
		if(encPassword.equals(user.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
	
	public int updateInfo(int id, String userName, String email, String password) {		
		String encPassword = EncryptUtils.md5(password);
		return userDAO.updateUser(id,userName,email,encPassword);
	}
	
	public List<User> getAllUsers() {
		return userDAO.selectAllUsers();
	}
	
	public List<BBS> showAllBBS() {
		return mainBO.showAllBBS();
	}
}
