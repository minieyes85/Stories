package com.minieyes.stories.bbs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.minieyes.stories.bbs.bo.BBSBO;
import com.minieyes.stories.bbs.model.BBS;
import com.minieyes.stories.bbs.model.BBSDTO;
import com.minieyes.stories.bbs.model.Category;

@Controller
public class BBSController {
	
	@Autowired
	BBSBO bbsBO;
	
	@GetMapping("/bbs")
	public String bbsView(
			@RequestParam(value="bbsId", required=false) int id,
			Model model) {
		
		BBS bbs = bbsBO.getBBS(id);
		List<Category> categories = bbsBO.getCategories(id);
		List<BBSDTO> articles = bbsBO.showBBS(id);
		model.addAttribute("bbs", bbs);
		model.addAttribute("categories", categories);
		model.addAttribute("articles", articles);
		
		
		return "bbs";
	}
}
