package com.minieyes.stories.bbs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.minieyes.stories.bbs.bo.BBSBO;
import com.minieyes.stories.bbs.model.Article;
import com.minieyes.stories.bbs.model.BBS;
import com.minieyes.stories.bbs.model.BBSDTO;
import com.minieyes.stories.bbs.model.Category;
import com.minieyes.stories.bbs.model.Comment;

@Controller
public class BBSController {
	
	@Autowired
	BBSBO bbsBO;
	
	@GetMapping("/bbs")
	public String bbsView(
			@RequestParam(value="bbsId", required=false) int id,
			Model model) {
		
		List<BBS> allbbs = bbsBO.showAllBBS();
				
		BBS bbs = bbsBO.getBBS(id);
		List<Category> categories = bbsBO.getCategories(id);
		List<BBSDTO> articles = bbsBO.showBBS(id);
		model.addAttribute("bbs", bbs);
		model.addAttribute("categories", categories);
		model.addAttribute("articles", articles);
		model.addAttribute("allbbs", allbbs);
		
		return "bbs";
	}
	
	@GetMapping("/article/createView")
	public String articleCreateView(
			@RequestParam("bbsId") int bbsId,
			HttpServletRequest req,
			Model model) {
		
		List<BBS> allbbs = bbsBO.showAllBBS();
		model.addAttribute("allbbs", allbbs);
		
		BBS bbs = bbsBO.getBBS(bbsId);
		model.addAttribute("bbs", bbs);
		
		List<Category> categories = bbsBO.getCategories(bbsId);
		model.addAttribute("categories", categories);
		
		
		return "articleCreate";
	}
	
	@GetMapping("/article/detailView")
	public String articleDetailView(
			@RequestParam("articleId") int articleId,
			Model model) {
		
		List<BBS> allbbs = bbsBO.showAllBBS();
		model.addAttribute("allbbs", allbbs);
		
		Article article = bbsBO.getArticle(articleId);
		model.addAttribute("article", article);
		
		BBS bbs = bbsBO.getBBS(article.getBbsId());
		model.addAttribute("bbs", bbs);
				
		List<Category> categories = bbsBO.getCategories(article.getBbsId());
		model.addAttribute("categories", categories);
		
		// 댓글들 불러오기
		List<Comment> comments = bbsBO.getComments(articleId);
		model.addAttribute("comments", comments);
				
		return "articleDetail";
	}
	
	@GetMapping("/article/updateView")
	public String articleUpdateView(
			@RequestParam("articleId") int articleId,
			Model model,
			HttpServletRequest req) {
		
		List<BBS> allbbs = bbsBO.showAllBBS();
		model.addAttribute("allbbs", allbbs);
		
		Article article = bbsBO.getArticle(articleId);
		model.addAttribute("article", article);
		
		BBS bbs = bbsBO.getBBS(article.getBbsId());
		model.addAttribute("bbs", bbs);
		
		List<Category> categories = bbsBO.getCategories(article.getBbsId());
		model.addAttribute("categories", categories);
		
		
		return "articleUpdate";
	}
}
