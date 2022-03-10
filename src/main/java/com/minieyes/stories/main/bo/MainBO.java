package com.minieyes.stories.main.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minieyes.stories.bbs.bo.BBSBO;
import com.minieyes.stories.bbs.model.ArticleDTO;
import com.minieyes.stories.bbs.model.BBS;
import com.minieyes.stories.main.model.MainDTO;

@Service
public class MainBO {
	
	@Autowired
	BBSBO bbsBO;
	
	public List<BBS> showAllBBS() {
		return bbsBO.showAllBBS(); 
	}
	
	public List<MainDTO> getMainBBS(){
		
		List<MainDTO> allBBSForMain = new ArrayList<>();
		
		// 게시판 아이디 리스트
		List<BBS> allBBS = bbsBO.showAllBBS();
		
		for(BBS bbs:allBBS) {
			int bbsId = bbs.getId();
			List<ArticleDTO> ArticleListForMain = bbsBO.getMainBBS(bbsId);
			MainDTO mainDTO = new MainDTO();
			mainDTO.setBbs(bbs);
			mainDTO.setArticleList(ArticleListForMain);
			allBBSForMain.add(mainDTO);
		}
		
		return allBBSForMain;
	}
}
