package com.minieyes.stories.main.model;

import java.util.List;

import com.minieyes.stories.bbs.model.ArticleDTO;
import com.minieyes.stories.bbs.model.BBS;

public class MainDTO {

	private BBS bbs;
	private List<ArticleDTO> articleList;
	
	public BBS getBbs() {
		return bbs;
	}
	public void setBbs(BBS bbs) {
		this.bbs = bbs;
	}
	public List<ArticleDTO> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<ArticleDTO> articleList) {
		this.articleList = articleList;
	}	
	
}
