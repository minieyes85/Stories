package com.minieyes.stories.bbs;

import java.util.ArrayList;
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
import com.minieyes.stories.bbs.model.BBS;
import com.minieyes.stories.bbs.model.ArticleDTO;
import com.minieyes.stories.bbs.model.Category;
import com.minieyes.stories.bbs.model.Comment;

@Controller
public class BBSController {
	
	@Autowired
	BBSBO bbsBO;
	
	@GetMapping("/bbs")
	public String bbsView(
			@RequestParam(value="bbsId", required=false) int bbsId,
			@RequestParam(value="page", required=false, defaultValue="1") Integer pageNo,
			Model model) {
		// header 게시판 표시
		List<BBS> allbbs = bbsBO.showAllBBS();
		model.addAttribute("allbbs", allbbs);
		
		// target 게시판 표시
		BBS bbs = bbsBO.getBBS(bbsId);
		model.addAttribute("bbs", bbs);
		List<Category> categories = bbsBO.getCategories(bbsId);
		model.addAttribute("categories", categories);
		
		// 게시판 게시글 표시
		List<ArticleDTO> articles = bbsBO.showBBS(bbsId);
		model.addAttribute("articles", articles);
		
		// 페이지표시
		// 페이지수 없을떄
		if(pageNo == null) {
			pageNo = 1;			
		}
		
		List<Integer> pageNOs = new ArrayList<>();

		// 총 페이지
		int lastPageNo = bbsBO.getLastPageNo(bbsId);
		
		// 총 페이지 < 10
		if(lastPageNo < 10) {
			for(int i = 1; i <= lastPageNo; i++ ) {
				pageNOs.add(i);
			}
			model.addAttribute("fwdPage", null);
			model.addAttribute("awdPage", null);
		// 총 페이지 >= 10
		} else {
			
			// 페이지 초반 <= 5
			if(pageNo <= 5) {
				for(int i = 1 ; i < 10 ; i++) {
					pageNOs.add(i);						
				}
				model.addAttribute("fwdPage", null);
				model.addAttribute("awdPage", 10);
				
			// 페이지 중반
			} else if((pageNo > 5) && (pageNo <= lastPageNo - 5)) {
				for(int i = pageNo-4 ; i < pageNo+5 ; i++) {
					pageNOs.add(i);
				}
				int fwdPage = pageNo - 5;
				model.addAttribute("fwdPage", fwdPage);
				int awdPage = pageNo + 5;
				model.addAttribute("awdPage", awdPage);
				
			// 페이지 후반 >= lastPageNo - 5
	 		} else {
	 			for(int i = lastPageNo-8 ; i <= lastPageNo ; i++) {
	 				pageNOs.add(i);
	 			}
	 			int fwdPage = lastPageNo-9;
	 			model.addAttribute("fwdPage", fwdPage);
	 			model.addAttribute("awdPage", null);	 			
	 		}
		}		
		
		model.addAttribute("pageNOs", pageNOs);
		model.addAttribute("pageNO", pageNo);
		
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
