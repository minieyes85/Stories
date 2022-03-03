package com.minieyes.stories.main;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.minieyes.stories.bbs.model.ArticleDTO;
import com.minieyes.stories.bbs.model.BBS;
import com.minieyes.stories.main.bo.MainBO;

@Controller
public class MainController {

	@Autowired
	MainBO mainBO;
	
	@RequestMapping("/")
	public String mainView(Model model) {
		
		List<BBS> allbbs = mainBO.showAllBBS();
		model.addAttribute("allbbs", allbbs);
		
		List<Map<BBS, List<ArticleDTO>>> allBBSForMain = mainBO.getMainBBS();
		model.addAttribute("allBBSForMain", allBBSForMain);		
		
		return "main";
	}
}
