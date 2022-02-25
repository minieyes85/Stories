package com.minieyes.stories.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minieyes.stories.user.bo.UserBO;
import com.minieyes.stories.user.model.User;

@RestController
public class UserRestController {

	@Autowired
	UserBO userBO;
	
	@PostMapping("/user/signUp")
	public Map<String, String> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("userName") String userName,
			@RequestParam("email") String email,
			@RequestParam("password") String password) {
		
		int count = userBO.addUser(loginId, userName, email, password);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");			
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	@GetMapping("/user/isDuplicatedId")
	public Map<String, String> checkDuplicatedId(@RequestParam("loginId") String loginId) {
		
		Map<String,String> result = new HashMap<>();
		
		if(userBO.findUser(loginId)) {
			result.put("isDuplicated","true");
		} else {
			result.put("isDuplicated","false");
		}
		
		return result;
	}
	
	@PostMapping("/user/signIn")
	public Map<String, String> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		Map<String,String> result = new HashMap<>();
		
		User signedUser = userBO.getUser(loginId, password);
		
		
		if(signedUser != null) {
			session.setAttribute("userId", signedUser.getId());
			session.setAttribute("userName", signedUser.getUserName());
			result.put("result", "success");
			result.put("userName", signedUser.getUserName());
		} else {
			result.put("result", "fail");			
		}
		
		return result;		
	}
	
	@GetMapping("/user/signOut")
	public Map<String, String> signOut(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		Map<String, String> result = new HashMap<>();
		
		if(session.getAttribute("userId") == null) {
			result.put("result", "success");			
		} else {
			result.put("result", "fail");						
		}
		
		return result;
	}
	
	@PostMapping("/user/checkPassword")
	public Map<String, String> checkPassword(
			HttpServletRequest req,
			@RequestParam("password") String password){
		
		HttpSession session = req.getSession();
		int id = (int) session.getAttribute("userId");
		
		boolean flag = userBO.checkPassword(id, password);				
		
		Map<String, String> result = new HashMap<>();
		
		if(flag) {
			result.put("result", "true");			
		} else {
			result.put("result", "false");						
		}
		
		return result;
	}
	
	@PostMapping("/user/update")
	public Map<String, String> updateUserInfo(
			@RequestParam("userName") String userName,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpServletRequest req){
		
		HttpSession session = req.getSession();
		
		int userId = (Integer) session.getAttribute("userId");
		
		int count = userBO.updateInfo(userId, userName, email, password);
		
		Map<String, String> result = new HashMap<>();

		if(count >= 1) {
			result.put("result", "success");
		} else {
			result.put("result","fail");
		}
		
		return result;		
	}
}
