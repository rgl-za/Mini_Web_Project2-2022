package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.javaex.service.BlogService;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
//	@RequestMappnig()
//	public ModelAndView contentList(ModelAndView mav){
//		mav.addObject("postlist", blogService.contentList());
//		mav.setViewName("blog-main");
//		return mav;
//	}
//	
//	public ModelAndView cateList(ModelAndView mav){
//		System.out.println("service -> " + blogService.cateList());
//		mav.addObject("catelist", blogService.cateList());
//		mav.setViewName("blog-main");
//		return mav;
//	}
}
