package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BlogVo> contentList(){
		System.out.println(sqlSession);
		return sqlSession.selectList("postXml.postList");
	}
	
	public int insertBlog(BlogVo blogVo) {
		sqlSession.insert("blogXml.insertBlog", blogVo);
		return sqlSession.selectOne("blogXml.selectUserNo", blogVo);
	}
	
	public BlogVo settingBlog(String id){
		return sqlSession.selectOne("blogXml.selectSetting", id);
	}
}
