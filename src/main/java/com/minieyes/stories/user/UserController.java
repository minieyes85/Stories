package com.minieyes.stories.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.minieyes.stories.user.bo.UserBO;
import com.minieyes.stories.user.model.User;

@Controller
public class UserController {
	
	@Autowired
	UserBO userBO;
	
	@GetMapping("/user/updateView")
	public String userUpdateView(
			Model model,
			HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		int signedId = (Integer) session.getAttribute("userId");
		
		User signedUser = userBO.getSignedUser(signedId);
		model.addAttribute("signedUser", signedUser);
		
		return "userUpdate";
	}
}
