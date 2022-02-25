package com.minieyes.stories.main.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minieyes.stories.bbs.bo.BBSBO;
import com.minieyes.stories.bbs.model.BBS;

@Service
public class MainBO {
	
	@Autowired
	BBSBO bbsBO;
	
	public List<BBS> showAllBBS() {
		return bbsBO.showAllBBS(); 
	}
}
