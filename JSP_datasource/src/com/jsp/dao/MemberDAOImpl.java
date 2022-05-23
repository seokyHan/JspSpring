package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public List<MemberVO> selectMemberList(SqlSession session) throws SQLException {

		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList");
		return memberList;
	
	}

	@Override
	public MemberVO selectMemberDetail(SqlSession session, String id) throws SQLException {

		MemberVO vo = session.selectOne("Member-Mapper.selectMemberDetail", id);
		
		
		
		return vo;
	}
	

	@Override
	public int deleteMember(SqlSession session, String id) throws SQLException {
        int result = 0;
		
		result = session.delete("Member-Mapper.deleteMember", id);
		
		return result;
	}

	@Override
	public int updateMember(SqlSession session, MemberVO vo) throws SQLException {
		   int result = 0;
		
		   result = session.update("Member-Mapper.udateMember", vo);
		return result;
	}

	@Override
	public int insertMember(SqlSession session, MemberVO vo) throws SQLException {
		 int result = 0;
			
		 result = session.insert("Member-Mapper.insertMember", vo);
		return result;
	}
	
}
