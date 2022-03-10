package com.minieyes.stories.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageManager {
	public static List<Integer> getPageList(int lastPageNo, int currentPage){
		
		List<Integer> allPages = new ArrayList<>();
		
		if(lastPageNo < 10) {
			
			// 총 페이지 < 10
			for(int i = 1; i <= lastPageNo; i++) {
				allPages.add(i);
			}
			
			// 총 페이지 >= 10
		} else {			
			// 페이지 초반 <= 5
			if(currentPage <= 5) {
				for(int i = 1; i < 10; i++) {
					allPages.add(i);
				}
			// 페이지 중반
			} else if((currentPage > 5) && (currentPage <= lastPageNo - 5)) {
				for(int i = currentPage-4 ; i < currentPage+5 ; i++) {
					allPages.add(i);
				}
			// 페이지 후반 >= lastPageNo - 5
			} else {
				for(int i = lastPageNo-8 ; i <= lastPageNo ; i++) {
					allPages.add(i);
				}
			}
		}
		
		return allPages;		
	}
	
	public static Map<String, Integer> getBoudnaryPages(int lastPageNo, int currentPage) {
		
		Map<String, Integer> result = new HashMap<>();
		
		if(lastPageNo < 10) {
			
			// 총 페이지 < 10
			result.put("aftPage", null);
			result.put("fwdPage", null);
			
			// 총 페이지 >= 10
		} else {			
			// 페이지 초반 <= 5
			if(currentPage <= 5) {
				result.put("aftPage", null);
				result.put("fwdPage", 10);
			// 페이지 중반
			} else if((currentPage > 5) && (currentPage <= lastPageNo - 5)) {
				result.put("aftPage", currentPage - 5);
				result.put("fwdPage", currentPage + 5);
			// 페이지 후반 >= lastPageNo - 5
			} else {
				result.put("aftPage", lastPageNo - 9);
				result.put("fwdPage", null);				
			}
		}
		
		return result;
	}
	
}
