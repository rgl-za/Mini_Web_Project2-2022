package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
//	@RequestMappnig()
	public ModelAndView contentList(ModelAndView mav){
		mav.addObject("postlist", blogService.contentList());
		mav.setViewName("blog-main");
		return mav;
	}
	
	public ModelAndView cateList(ModelAndView mav){
		System.out.println("service -> " + blogService.cateList());
		mav.addObject("catelist", blogService.cateList());
		mav.setViewName("blog-main");
		return mav;
	}
	
//	@ResponseBody
//	@RequestMapping(value="/{id}/admin/basic", produces="application/text; charset=utf-8", method=RequestMethod.POST)
//	public String insertBlog(@ModelAttribute BlogVo blogVo) {
//		System.out.println(blogVo);
//		System.out.println(blogVo.getFile());
////		System.out.println(blogVo.getFile().hashCode());
//		blogService.insertBlog(blogVo);
//		return "blog/admin/blog-admin-basic";
//	}
	
	@ResponseBody
	@RequestMapping(value="/upload/insert", produces="application/text; charset=utf-8", method=RequestMethod.POST)
	public String insert(BlogVo blogVo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		blogVo.setId(authUser.getId());
		blogService.insertBlog(blogVo);
		return "redirect:/${id}";
	}
}
