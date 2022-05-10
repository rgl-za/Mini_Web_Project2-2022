package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CateDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;

@Service
public class BlogService {
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
}
