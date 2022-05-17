package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CateDao;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CateDao cateDao;
	
	@Autowired
	private PostDao postDao;
	
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
		postDao.write(postVo);
	}
	
	// 블로그 포스트 리스트 클릭 시 해당 데이터 조회
	public PostVo getPostOne(String postNo) {
		return postDao.getPostOne(postNo);
	}

	public List<PostVo> getPostList(String cateNo) {
		return postDao.getPostList(cateNo);
	}
	
	public void insertBlog(BlogVo blogVo) {
		int userNo = blogDao.insertBlog(blogVo); // 키값 필요
//		blogDao.insertBlog(blogVo);
		
	}
	
	public BlogVo settingBlog(String id) {
		return blogDao.settingBlog(id);
	}
}
