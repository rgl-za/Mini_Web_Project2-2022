package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void write(PostVo postVo) {
		sqlSession.insert("postXml.insertPost", postVo);
	}

	public PostVo getPostOne(String postNo) {
		return sqlSession.selectOne("postXml.getPostOne", postNo);
	}
	
	public List<PostVo> getPostList(String cateNo) {
		return sqlSession.selectList("postXml.getPostList", cateNo);
	}

}
