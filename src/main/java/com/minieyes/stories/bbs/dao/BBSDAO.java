package com.minieyes.stories.bbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.minieyes.stories.bbs.model.Article;
import com.minieyes.stories.bbs.model.ArticleDTO;
import com.minieyes.stories.bbs.model.BBS;
import com.minieyes.stories.bbs.model.Category;
import com.minieyes.stories.bbs.model.Comment;
import com.minieyes.stories.bbs.model.Tag;

@Repository
public interface BBSDAO {
	
	public List<BBS> selectAllBBS();
	
	public BBS selectTargetBBS(@Param("bbsId") int bbsId);
	
	public List<Category> selectCategoriesByBBS(@Param("bbsId") int bbsId);
	
	public List<ArticleDTO> selectBBS(
			@Param("bbsId") int bbsId,
			@Param("offset") int offset);
	
	public List<ArticleDTO> selectBBSByTitle(
			@Param("bbsId") int bbsId,
			@Param("title") String search,
			@Param("offset") int offset);
	
	public List<ArticleDTO> selectBBSByUserName(
			@Param("bbsId") int bbsId,
			@Param("userName") String search,
			@Param("offset") int offset);
	
	public List<ArticleDTO> selectBBSByCategory(
			@Param("bbsId") int bbsId,
			@Param("categoryId") String search,
			@Param("offset") int offset);
	
	public List<ArticleDTO> selectBBSForMain(
			@Param("bbsId") int bbsId);
	
	public int insertNewArticle(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("bbsId") int bbsId,
			@Param("categoryId") int categoryId,
			@Param("title") String title,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public int insertReply(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("bbsId") int bbsId,
			@Param("categoryId") int categoryId,
			@Param("grpId") int grpId,
			@Param("depth") int depth,
			@Param("title") String title,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
		
	public Article selectArticle(
			@Param("id") int articleId);
	
	public int selectLastArticleId();
	
	public int selectArticleIdAtMoment(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("title") String title);
	
	public int selectCountArticleByBBSID(@Param("bbsId") int bbsId);
	
	public int selectCountArticleByBBSIDAndTitle(
			@Param("bbsId") int bbsId,
			@Param("title") String title);
	
	public int selectCountArticleByBBSIDAndUserName(
			@Param("bbsId") int bbsId,
			@Param("userName") String UserName);
	
	public int selectCountArticleByBBSIDAndCategory(
			@Param("bbsId") int bbsId,
			@Param("categoryId") String category);
	
	public int updateArticle(
			@Param("id") int articleId,
			@Param("categoryId") int categoryId,
			@Param("title") String title,
			@Param("content") String content);
	
	public int updateNewArticleGrpId(@Param("newArticleId") int newArticleId);
	
	public int deleteArticle(@Param("id") int articleId);
	
	public int insertNewComment(
			@Param("articleId") int articleId,
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("content") String content);
	
	public int insertReComment(
			@Param("articleId") int articleId,
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("grpId") int grpId,
			@Param("depth") int depth,
			@Param("content") String content);
	
	public int selectLastCommentId();
	
	public int updateNewCommentGrpId(@Param("newCommentId") int newCommentId);
	
	public List<Comment> selectCommentByArticleId(@Param("articleId") int articleId);
	
	public int selectCommentCountByArticleId(@Param("articleId") int articleId);
	
	public int deleteComment(@Param("id") int commentId);
	
	public int deleteCommentByArticleId(@Param("articleId") int articleId);
	
	public int updateComment(
			@Param("id") int commentId,
			@Param("content") String content);
	
	public int selectRecommend(
			@Param("articleId") int articleId,
			@Param("userId") int userId);
	
	public int insertRecommend(
			@Param("articleId") int articleId,
			@Param("userId") int userId);
	
	public int deleteRecommend(
			@Param("articleId") int articleId,
			@Param("userId") int userId);
	
	public int selectRecommendByArticleId(@Param("articleId") int articleId);
	
	public Tag selectExistTag(
			@Param("articleId") int articleId,
			@Param("title") String tag);
	
	public int insertTag(
			@Param("articleId") int articleId,
			@Param("title") String tag);
	
	public List<Tag> selectTagsByArticleId(@Param("articleId") int articleId);
	
	public int deleteTag(@Param("id") int tagId);
	
	public int deleteTagByArticleId(@Param("articleId") int articleId);
	
	public int updateArticleByAdmin(
			@Param("articleId") int articleId,
			@Param("bbsId") int bbsId,
			@Param("categoryId") int categoryId);
	
}
