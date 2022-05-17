package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javaex.service.BlogService;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
public class MainController {
	
	@Autowired
	private BlogService blogService;
	
	/*JBlog 硫붿씤 �뤌 異쒕젰*/
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main() {
		return "main/index";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView blog(@PathVariable String id, ModelAndView mav) {
		mav.addObject("postlist", blogService.contentList());
		mav.addObject("catelist", blogService.cateList(id));
		mav.setViewName("blog/blog-main");
		return mav;
	}
	
//	@RequestMapping(value="/{id}/{postNo}")
//	public ModelAndView post(@PathVariable("id") String id, @PathVariable("postNo") String postNo, ModelAndView mav) {
//		mav.addObject("postOne", blogService.getPostOne(postNo));
//		mav.addObject("postlist", blogService.contentList());
//		mav.addObject("catelist", blogService.cateList(id));
//		mav.setViewName("blog/blog-main");
//		return mav;
//	}
	
	@ResponseBody
	@RequestMapping(value = "/cateNo", method=RequestMethod.POST)
	public List<PostVo> cate(@ModelAttribute CateVo cateVo) {
		System.out.println(cateVo.getCateNo());
		return blogService.getPostList(cateVo.getCateNo());
	}
	
	
	@RequestMapping(value="/{id}/admin/{url}")
	   public ModelAndView blog(@PathVariable String id, @PathVariable("url") String url, ModelAndView mav) {
	      System.out.println(url);
	      if(url.equals("basic")) {
	         mav.addObject("settingBlog", blogService.settingBlog(id));
	    	  mav.setViewName("blog/admin/blog-admin-basic");
	         
	      }
	      else if(url.equals("category")) {
	         mav.addObject("catelist", blogService.cateList(id));
	         mav.setViewName("blog/admin/blog-admin-cate");
	      }
	      else{
	         mav.addObject("catelist", blogService.cateList(id));
	         mav.setViewName("blog/admin/blog-admin-write");
	      }
	      return mav;
	   }
	
	@RequestMapping(value="/category/insert", method=RequestMethod.POST)
	public String insertCate(@ModelAttribute CateVo cateVo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		cateVo.setId(authUser.getId());
		blogService.insertCate(cateVo);
		return "redirect:/"+authUser.getId()+"/admin/category";
	}
	
	@ResponseBody
	@RequestMapping(value="/category/delete", method=RequestMethod.POST)
	public boolean deleteCate(@ModelAttribute CateVo cateVo) {	
		if (cateVo.getPostCount() == 0) {
			blogService.deleteCate(cateVo.getCateNo());
			return true;
		}

		return false;
	}

	@RequestMapping(value="/{id}/write/insert", method=RequestMethod.POST)
	public String insertWrite(@PathVariable String id, @ModelAttribute PostVo postVo) {
		blogService.write(postVo);
		return "redirect:/"+id+"/admin/write";
	}
	
}
