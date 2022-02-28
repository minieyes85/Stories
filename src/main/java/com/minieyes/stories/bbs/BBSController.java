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
			@RequestParam("bbsId") int bbsId,
			@RequestParam("articleId") int articleId,
			HttpServletRequest req,
			Model model) {
	
		BBS bbs = bbsBO.getBBS(bbsId);
		model.addAttribute("bbs", bbs);
				
		List<Category> categories = bbsBO.getCategories(bbsId);
		model.addAttribute("categories", categories);
		
		Article article = bbsBO.getArticle(articleId);
		model.addAttribute("article", article);
		
		return "articleDetail";
	}
}
