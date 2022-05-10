package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CateVo;

@Repository
public class CateDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<CateVo> cateList(CateVo cateVo){
		System.out.println("---> sqlSession.selectList()");
		System.out.println(sqlSession);
		return sqlSession.selectList("cateXml.cateList");
	}
	
	public void insertCate(CateVo cateVo) {
		sqlSession.insert("cateXml.insertCate", cateVo);		
	}
}
