package com.minieyes.stories.bbs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minieyes.stories.bbs.bo.BBSBO;

@RestController
public class BBSRestController {

	@Autowired
	BBSBO bbsBO;
	
	@PostMapping("/article/create")
	public Map<String, String> createArticle(
			@RequestParam("bbsId") int bbsId,
			@RequestParam("category") int categoryId,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			HttpServletRequest req){
		
		HttpSession session = req.getSession();
		
		int userId = (Integer) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		
		int count = bbsBO.createNewArticle(userId, userName, bbsId, categoryId, title, content);
		
		Map<String, String> result = new HashMap<>();

		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");			
		}		
		
		return result;
		
	}
	
	@PostMapping("/article/update")
	public Map<String, String> updateArticle(
			@RequestParam("articleId") int articleId,
			@RequestParam("categoryId") int categoryId,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			HttpServletRequest req){
		
		int count = bbsBO.updateArticle(articleId, categoryId, title, content);

		Map<String, String> result = new HashMap<>();

		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}

		return result;		
	}
	
	@PostMapping("/article/delete")
	public Map<String, String> deleteArticle(
			@RequestParam("articleId") int articleId){
		
		int count = bbsBO.removeArticle(articleId);
		
		Map<String, String> result = new HashMap<>();

		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}

		return result;	
	}
}
