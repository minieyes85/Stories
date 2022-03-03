package com.minieyes.stories.bbs.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.minieyes.stories.bbs.dao.BBSDAO;
import com.minieyes.stories.bbs.model.Article;
import com.minieyes.stories.bbs.model.ArticleDTO;
import com.minieyes.stories.bbs.model.BBS;
import com.minieyes.stories.bbs.model.Category;
import com.minieyes.stories.bbs.model.Comment;
import com.minieyes.stories.common.FileManagerService;

@Service
public class BBSBO {
	
	@Autowired
	BBSDAO bbsDAO;
	
	public List<BBS> showAllBBS() {
		return bbsDAO.selectAllBBS(); 
	}
	
	public BBS getBBS(int bbsId) {
		return bbsDAO.selectTargetBBS(bbsId);
	}
	
	public List<Category> getCategories(int bbsId){
		return bbsDAO.selectCategoriesByBBS(bbsId);
	}
	
	public List<ArticleDTO> showBBS(int bbsId){
		
		//댓글 추천 갯수 포함
		List<ArticleDTO> bbsDTOs = bbsDAO.selectBBS(bbsId);
		
		for(ArticleDTO bbsDTO : bbsDTOs) {
			int articleId = bbsDTO.getArticleId();
			int countComment = bbsDAO.selectCommentCountByArticleId(articleId);
			int countRecommend = bbsDAO.selectRecommendByArticleId(articleId);
			bbsDTO.setCommentNo(countComment);
			bbsDTO.setRecommendNo(countRecommend);
		}
				
		return bbsDTOs;
	}
	
	public int createNewArticle(
			int userId,
			String userName,
			int bbsId,
			int categoryId,
			String title,
			String content,
			MultipartFile file) {
		
		//첨부파일 첨부 추가
		String imagePath = FileManagerService.saveFile(userId, file);
		
		//태그 나눠서 저장 추가
		
		return bbsDAO.insertNewArticle(userId, userName, bbsId, categoryId, title, content, imagePath);
	}
	
	public Article getArticle(int articleId) {
		return bbsDAO.selectArticle(articleId);
	}
	
	public int updateArticle(int articleId, int categoryId, String title, String content) {
		return bbsDAO.updateArticle(articleId, categoryId, title, content);
	}
	
	public int removeArticle(int articleId) {
		
		//삭제 대상 게시글 파일 삭제
		Article article = bbsDAO.selectArticle(articleId);
		FileManagerService.removeFile(article.getImagePath());
		
		//삭제 대상 게시글 댓글 삭제
		bbsDAO.deleteCommentByArticleId(articleId);
		
		return bbsDAO.deleteArticle(articleId);
	}
	
	public int createNewComment(int articleId, int userId, String userName, String content) {
		return bbsDAO.insertNewComment(articleId, userId, userName, content);
	}
	
	public List<Comment> getComments(int articleId){
		return bbsDAO.selectCommentByArticleId(articleId);
	}
	
	public int removeComment(int commentId) {
		return bbsDAO.deleteComment(commentId);
	}
	
	public int modifyComment(int commentId, String content) {
		return bbsDAO.updateComment(commentId, content);
	}
	
	public int recommend(int articleId, int userId) {
		
		// 추천이 있는지 확인
		int count = bbsDAO.selectRecommend(articleId, userId);
		
		if(count == 0) {
			return bbsDAO.insertRecommend(articleId, userId);
		} else {
			return bbsDAO.deleteRecommend(articleId, userId);
		}	
	}
	
	public int getRecommend(int articleId) {
		return bbsDAO.selectRecommendByArticleId(articleId);
	}
	
	public boolean getIsRecommend(int articleId, int userId) {
		
		int count = bbsDAO.selectRecommend(articleId, userId);
		
		if(count == 0) {
			return false;
		} else {
			return true;
		}		
	}
	
	public int getLastPageNo(int bbsId) {
		
		int articleCount = bbsDAO.selectCountArticleByBBSID(bbsId);
		int pageNo = 0;
		
		if(articleCount%20 > 0) {
			pageNo = articleCount / 20 + 1;
		} else {
			pageNo = articleCount / 20;
		}
		
		return pageNo;		
	}
		
	public List<ArticleDTO> getMainBBS(int bbsId){
		List<ArticleDTO> articles = bbsDAO.selectBBSForMain(bbsId);
		for(ArticleDTO article:articles) {
			// 댓글 갯수 추가
			int articleId = article.getArticleId();
			article.setCommentNo(bbsDAO.selectCommentCountByArticleId(articleId));
		}
		
		return articles;
	}
}
