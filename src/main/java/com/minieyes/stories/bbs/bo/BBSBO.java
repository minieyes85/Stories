package com.minieyes.stories.bbs.bo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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
import com.minieyes.stories.bbs.model.Tag;
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
	
	public List<ArticleDTO> showBBS(int bbsId, Integer searchType, String searchInput, int currentPage){
		
		int offset = (currentPage-1)*20;
		List<ArticleDTO> bbsDTOs = new ArrayList<>();
		
		//대상 글 조회
		
		if(searchType == null) {
			bbsDTOs = bbsDAO.selectBBS(bbsId, offset);			
		} else if(searchType == 1) {
			bbsDTOs = bbsDAO.selectBBSByTitle(bbsId, searchInput, offset);			
		} else if(searchType == 2) {
			bbsDTOs = bbsDAO.selectBBSByUserName(bbsId, searchInput, offset);
		} else if(searchType == 3) {
			bbsDTOs = bbsDAO.selectBBSByCategory(bbsId, searchInput, offset);			
		}
		
		//댓글 추천 갯수 포함
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
			MultipartFile file,
			String tagsString) {
		
		//첨부파일 첨부 추가
		String imagePath = FileManagerService.saveFile(userId, file);
		
		int count = bbsDAO.insertNewArticle(userId, userName, bbsId, categoryId, title, content, imagePath);
		
		if(count == 1 ) {
			
			// 지금 추가한 글 id 조회
			int articleId = bbsDAO.selectArticleIdAtMoment(userId, userName, title);
			
			//태그 나누기
			String[] tagStringArray = tagsString.split(",");
			
			//중복제거
			LinkedHashSet<String> tags = new LinkedHashSet<>();
			for(String tag:tagStringArray) {
				String trimedTag = tag.trim();
				tags.add(trimedTag);
			}
			
			// 각 태그 db에 추가
			for(String tag:tags) {
				bbsDAO.insertTag(articleId, tag);
			}
			
			return count;
			
		} else {
			
			return count;
		}		
		 
	}
	
	public Article getArticle(int articleId) {
		return bbsDAO.selectArticle(articleId);
	}
	
	public int updateArticle(int articleId, int categoryId, String title, String content, String tagsString) {
		
		// 태그 처리
		
		//태그 나누기
		String[] tagStringArray = tagsString.split(",");
		
		//중복제거
		LinkedHashSet<String> tags = new LinkedHashSet<>();
		for(String tag:tagStringArray) {
			String trimedTag = tag.trim();
			tags.add(trimedTag);
		}
		
		// 각 태그 db에 추가
		for(String tag:tags) {
			
			// 태그가 있는지 검사
			Tag targetTag = bbsDAO.selectExistTag(articleId, tag);
			// 태그가 없으면 추가
			if(targetTag == null) {
				bbsDAO.insertTag(articleId, tag);				
			}			
		}		
		
		return bbsDAO.updateArticle(articleId, categoryId, title, content);
	}
	
	public int removeArticle(int articleId) {
		
		//삭제 대상 게시글 파일 삭제
		Article article = bbsDAO.selectArticle(articleId);
		FileManagerService.removeFile(article.getImagePath());
		
		//삭제 대상 게시글 댓글 삭제
		bbsDAO.deleteCommentByArticleId(articleId);
		
		//삭제 대상 게시글 태그 삭제
		bbsDAO.deleteTagByArticleId(articleId);
		
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
	
	public int getLastPageNo(int bbsId, Integer searchType, String searchInput) {
		
		int pageNo = 0;
		int articleCount = 0;

		if(searchType == null) {
			articleCount = bbsDAO.selectCountArticleByBBSID(bbsId);				
		} else if(searchType == 1) {
			articleCount = bbsDAO.selectCountArticleByBBSIDAndTitle(bbsId, searchInput);			
		} else if(searchType == 2) {
			articleCount = bbsDAO.selectCountArticleByBBSIDAndUserName(bbsId, searchInput);
		} else if(searchType == 3) {
			articleCount = bbsDAO.selectCountArticleByBBSIDAndCategory(bbsId, searchInput);
		}
		
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
	
	public List<Tag> getTags(int articleId){
		return bbsDAO.selectTagsByArticleId(articleId);
	}
	
	public int removeTag(int tagId) {
		return bbsDAO.deleteTag(tagId);
	}
}
