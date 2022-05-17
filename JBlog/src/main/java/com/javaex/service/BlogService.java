package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CateDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.UserVo;

@Service
public class BlogService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CateDao cateDao;
	
	public List<BlogVo> contentList(){
		return blogDao.contentList();
	}
	
	public List<CateVo> cateList(){
		return cateDao.cateList();
	}
	
	public void insertBlog(BlogVo blogVo) {
		int userNo = blogDao.insertBlog(blogVo); // 키값 필요
//		blogDao.insertBlog(blogVo);
		
	}
	
	public BlogVo settingBlog(String id) {
		return blogDao.settingBlog(id);
	}
}
