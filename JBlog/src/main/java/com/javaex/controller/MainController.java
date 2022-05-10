package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javaex.service.BlogService;

@Controller
public class MainController {
	
	@Autowired
	private BlogService blogService;
	
	/*JBlog 메인 폼 출력*/
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main() {
		return "main/index";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView blog(ModelAndView mav) {
		mav.addObject("postlist", blogService.contentList());
		mav.addObject("catelist", blogService.cateList());
		mav.setViewName("blog/blog-main");
		return mav;
	} 
}
