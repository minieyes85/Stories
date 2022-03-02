package com.minieyes.stories.bbs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest req){
		
		HttpSession session = req.getSession();
		
		int userId = (Integer) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		
		int count = bbsBO.createNewArticle(userId, userName, bbsId, categoryId, title, content, file);
		
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
	
	@PostMapping("/comment/create")
	public Map<String, String> createComment(
			@RequestParam("articleId") int articleId,
			@RequestParam("userId") int userId,
			@RequestParam("userName") String userName,
			@RequestParam("content") String content){
		
		int count = bbsBO.createNewComment(articleId, userId, userName, content);		
		
		Map<String, String> result = new HashMap<>();

		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}

		return result;
	}
	
	@PostMapping("/comment/delete")
	public Map<String, String> deleteComment(@RequestParam("commentId") int commentId){
		
		int count = bbsBO.removeComment(commentId);
		
		Map<String, String> result = new HashMap<>();

		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}

		return result;
	}
	
	@PostMapping("/comment/update")
	public Map<String, String> updateComment(
			@RequestParam("commentId") int commentId,
			@RequestParam("content") String content){
		
		int count = bbsBO.modifyComment(commentId, content);
		
		Map<String, String> result = new HashMap<>();

		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}

		return result;		
	}
}
