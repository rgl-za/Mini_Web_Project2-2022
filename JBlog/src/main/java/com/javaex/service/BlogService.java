package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CateDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CateDao cateDao;
	
	public List<BlogVo> contentList(){
		return blogDao.contentList();
	}
	
	public List<CateVo> cateList(String id){
		return cateDao.cateList(id);
	}
	
	// 블로그 카테고리 추가
	public void insertCate(CateVo cateVo) {
		cateDao.insertCate(cateVo);
	}
	
	// 블로그 카테고리 삭제
	public void deleteCate(int cateNo) {
		cateDao.deleteCate(cateNo);
	}
	
	// 블로그 글 작성
	public void write(PostVo postVo) {
		
	}
}
