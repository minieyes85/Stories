package com.minieyes.stories.bbs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.minieyes.stories.bbs.bo.BBSBO;
import com.minieyes.stories.bbs.model.Article;
import com.minieyes.stories.bbs.model.ArticleDTO;
import com.minieyes.stories.bbs.model.BBS;
import com.minieyes.stories.bbs.model.Category;
import com.minieyes.stories.bbs.model.Comment;
import com.minieyes.stories.bbs.model.Tag;
import com.minieyes.stories.common.PageManager;

@Controller
public class BBSController {
	
	@Autowired
	BBSBO bbsBO;
	
	@GetMapping("/bbs")
	public String bbsView(
			@RequestParam(value="bbsId", required=false) int bbsId,
			@RequestParam(value="searchType", required=false) Integer searchType,
			@RequestParam(value="searchInput", required=false, defaultValue="") String searchInput,
			@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage,
			Model model) {
		
		// header 게시판 표시
		List<BBS> allbbs = bbsBO.showAllBBS();
		model.addAttribute("allbbs", allbbs);
		
		// target 게시판 표시
		BBS bbs = bbsBO.getBBS(bbsId);
		model.addAttribute("bbs", bbs);
		List<Category> categories = bbsBO.getCategories(bbsId);
		model.addAttribute("categories", categories);
		
			
		// 페이지수 없을떄
		if (currentPage == null) {
			currentPage = 1;
		}

		// 총 페이지
		int lastPageNo = bbsBO.getLastPageNo(bbsId, searchType, searchInput);

		// 페이지표시
		List<Integer> allPages = PageManager.getPageList(lastPageNo, currentPage);
		model.addAttribute("allPages", allPages);
		model.addAttribute("fwdPage", PageManager.getBoudnaryPages(lastPageNo, currentPage).get("fwdPage"));
		model.addAttribute("aftPage", PageManager.getBoudnaryPages(lastPageNo, currentPage).get("aftPage"));

		// 현재 페이지
		model.addAttribute("pageNO", currentPage);

		// 게시판 게시글 표시
		List<ArticleDTO> articles = bbsBO.showBBS(bbsId, searchType, searchInput, currentPage);
		model.addAttribute("articles", articles);
		
		// 검색 결과 전달
		if(searchType == null) {
			model.addAttribute("isSearch", false);	
		} else {
			model.addAttribute("isSearch", true);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchInput", searchInput);			
		}
		
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
			HttpServletRequest req,
			Model model) {
		
		
		List<BBS> allbbs = bbsBO.showAllBBS();
		model.addAttribute("allbbs", allbbs);
		
		Article article = bbsBO.getArticle(articleId);
		model.addAttribute("article", article);
		
		BBS bbs = bbsBO.getBBS(article.getBbsId());
		model.addAttribute("bbs", bbs);
				
		List<Category> categories = bbsBO.getCategories(article.getBbsId());
		model.addAttribute("categories", categories);
		
		// 추천 불러오기
		// 추천 갯수
		int recommendCount = bbsBO.getRecommend(articleId);
		model.addAttribute("recommendCount", recommendCount);
		
		HttpSession session = req.getSession();
		
		int userId;
		boolean isRecommend;
		
		if(session.getAttribute("userId") == null) {
			isRecommend = false;	
		} else {
			userId = (Integer) session.getAttribute("userId");
			// 사용자가 추천 했는지?
			isRecommend = bbsBO.getIsRecommend(articleId, userId);
		}
		
		model.addAttribute("isRecommend", isRecommend);
		
		// 댓글들 불러오기
		List<Comment> comments = bbsBO.getComments(articleId);
		model.addAttribute("comments", comments);

		// 태그들 불러오기
		List<Tag> tags = bbsBO.getTags(articleId);
		model.addAttribute("tags", tags);
		
		
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
		
		// 태그들 불러오기
		List<Tag> tags = bbsBO.getTags(articleId);
		model.addAttribute("tags", tags);
				
		return "articleUpdate";
	}
}
