package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;

public class MemberServiceImpl implements MemberService {

	private SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			List<MemberVO> memberList = memberDAO.selectMemberList(session);
			return memberList;
		}finally {
			session.close();
		}
	}

	@Override
	public MemberVO getMemberDetail(String id) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			MemberVO memberDetail = memberDAO.selectMemberDetail(session, id);
			return memberDetail;
		}finally {
			session.close();
		}
	}

	@Override
	public int addMember(MemberVO vo) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			int result = memberDAO.insertMember(session, vo);
			return result;
		}finally {
			session.close();
		}
	}

	@Override
	public int modifyMember(MemberVO vo) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			int result = memberDAO.updateMember(session, vo);
			return result;
		}finally {
			session.close();
		}
	}

	@Override
	public int removeMember(String id) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			int result = memberDAO.deleteMember(session, id);
			return result;
		}finally {
			session.close();
		}
	}

}
