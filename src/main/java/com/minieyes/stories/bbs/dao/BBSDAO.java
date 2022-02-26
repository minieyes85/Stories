package com.minieyes.stories.bbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.minieyes.stories.bbs.model.BBS;
import com.minieyes.stories.bbs.model.BBSDTO;
import com.minieyes.stories.bbs.model.Category;

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
	
}
