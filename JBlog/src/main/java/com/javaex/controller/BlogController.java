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
	
	@ResponseBody
	@RequestMapping(value="/upload/insert", produces="application/text; charset=utf-8", method=RequestMethod.POST)
	public String insert(BlogVo blogVo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		blogVo.setId(authUser.getId());
		blogService.insertBlog(blogVo);
		return "redirect:/${id}";
	}
}
