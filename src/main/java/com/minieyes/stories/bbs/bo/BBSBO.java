package com.minieyes.stories.bbs.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minieyes.stories.bbs.dao.BBSDAO;
import com.minieyes.stories.bbs.model.BBS;
import com.minieyes.stories.bbs.model.BBSDTO;
import com.minieyes.stories.bbs.model.Category;

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
	
	public List<BBSDTO> showBBS(int bbsId){
		
		//추가로 댓글 추천 갯수 DTO에 포함시킬것
		
		return bbsDAO.selectBBS(bbsId);
	}
}
