package com.minieyes.stories.bbs.model;

public class ArticleDTO {
	
	private Article article;
	private String categoryTitle;
	private int countRecommend;
	private int countComment;
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public int getCountRecommend() {
		return countRecommend;
	}
	public void setCountRecommend(int countRecommend) {
		this.countRecommend = countRecommend;
	}
	public int getCountComment() {
		return countComment;
	}
	public void setCountComment(int countComment) {
		this.countComment = countComment;
	}
}
