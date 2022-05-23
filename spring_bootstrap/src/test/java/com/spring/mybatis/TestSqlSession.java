package com.spring.mybatis;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsp.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/context/root-context.xml")
public class TestSqlSession {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private SqlSession session;
	
	@Test
	public void testSqlSessionFactory() {
		Assert.assertNotNull(sqlSessionFactory);
	}
	
	@Test
	public void testSqlSession() throws SQLException{
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList");
		
		Assert.assertEquals(9, memberList.size());
		
	}
}
