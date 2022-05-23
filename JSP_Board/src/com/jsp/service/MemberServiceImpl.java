package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;
import com.jsp.exception.IdNotFoundException;
import com.jsp.exception.InvalidPasswordException;

public class MemberServiceImpl implements MemberService {
	
	private SqlSessionFactory sqlSessionFactory;
	private MemberDAO memberDAO;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public List<MemberVO> getMemberList(Criteria cri) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		
		List<MemberVO> memberList = null;
		try {
			memberList = memberDAO.selectMemberList(session, cri);
			
			session.commit();
			
		}catch (Exception e){
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return memberList;
	}

	@Override
	public Map<String, Object> getSearchMemberList(Criteria cri) throws Exception {
		Map<String,Object> dataMap =null;
		
		SqlSession session= sqlSessionFactory.openSession();
		
		try {
			List<MemberVO> memberList = memberDAO.selectMemberList(session, cri);
						
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(memberDAO.selectMemberListCount(session,cri));
			
			
			dataMap = new HashMap<String,Object>();
			dataMap.put("memberList", memberList);
			dataMap.put("pageMaker",pageMaker);
			
		}finally {
			session.close();
		}
		
		return dataMap;
	}

	@Override
	public void login(String id, String pwd) throws IdNotFoundException, InvalidPasswordException, SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
			MemberVO member = memberDAO.selectMemberById(session, id);
			if (member == null)
				throw new IdNotFoundException();
			if (!pwd.equals(member.getPwd()))
				throw new InvalidPasswordException();
			
		} finally {
			session.close();
		}
		
	}

	@Override
	public MemberVO getMember(String id) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			MemberVO member = memberDAO.selectMemberById(session, id);
			return member;
		} finally {
			session.close();
		}	
	}

	@Override
	public void regist(MemberVO member) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			System.out.println("service   :   " + member.getPicture());
			memberDAO.insertMember(session, member);
		} finally {
			session.close();
		}
		
	}

	@Override
	public void modify(MemberVO member) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			memberDAO.updateMember(session, member);
		} finally {
			session.close();
		}

	}

	@Override
	public void remove(String id) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			memberDAO.deleteMember(session, id);
		} finally {
			session.close();
		}
		
	}

	@Override
	public void enabled(String id, int enabled) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			memberDAO.enabledMember(session, id, enabled);
		} finally {
			session.close();
		}
		
	}
	
	
	
}
