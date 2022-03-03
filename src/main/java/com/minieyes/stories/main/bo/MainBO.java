package com.minieyes.stories.main.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minieyes.stories.bbs.bo.BBSBO;
import com.minieyes.stories.bbs.model.ArticleDTO;
import com.minieyes.stories.bbs.model.BBS;

@Service
public class MainBO {
	
	@Autowired
	BBSBO bbsBO;
	
	public List<BBS> showAllBBS() {
		return bbsBO.showAllBBS(); 
	}
	
	public List<Map<BBS, List<ArticleDTO>>> getMainBBS(){
		
		List<Map<BBS, List<ArticleDTO>>> allBBSForMain = new ArrayList<>();
		
		// 게시판 아이디 리스트
		List<BBS> allBBS = bbsBO.showAllBBS();
		
		for(BBS bbs:allBBS) {
			int bbsId = bbs.getId();
			List<ArticleDTO> ArticleListForMain = bbsBO.getMainBBS(bbsId);
			Map<BBS, List<ArticleDTO>> BBSForMain = new HashMap<>();
			BBSForMain.put(bbs, ArticleListForMain);
			allBBSForMain.add(BBSForMain);
		}
		
		return allBBSForMain;
	}
}
