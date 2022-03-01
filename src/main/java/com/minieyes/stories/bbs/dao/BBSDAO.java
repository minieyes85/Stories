package com.minieyes.stories.bbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.minieyes.stories.bbs.model.Article;
import com.minieyes.stories.bbs.model.BBS;
import com.minieyes.stories.bbs.model.BBSDTO;
import com.minieyes.stories.bbs.model.Category;
import com.minieyes.stories.bbs.model.Comment;

@Repository
public interface BBSDAO {
	
	public List<BBS> selectAllBBS();
	
	public BBS selectTargetBBS(@Param("bbsId") int bbsId);
	
	public List<Category> selectCategoriesByBBS(@Param("bbsId") int bbsId);
	
	public List<BBSDTO> selectBBS(
			@Param("bbsId") int bbsId);
	
	public int insertNewArticle(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("bbsId") int bbsId,
			@Param("categoryId") int categoryId,
			@Param("title") String title,
			@Param("content") String content);
	
	public Article selectArticle(
			@Param("id") int articleId);
	
	public int updateArticle(
			@Param("id") int articleId,
			@Param("categoryId") int categoryId,
			@Param("title") String title,
			@Param("content") String content);
	
	public int deleteArticle(@Param("id") int articleId);
	
	public int insertNewComment(
			@Param("articleId") int articleId,
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("content") String content);
	
	public List<Comment> selectCommentByArticleId(@Param("articleId") int articleId);
	
}
