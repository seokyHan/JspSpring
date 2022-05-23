package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MemberVO;

public interface MemberDAO {

	
	//회원리스트 조회
	List<MemberVO> selectMemberList(SqlSession session) throws SQLException;
	
	MemberVO selectMemberDetail(SqlSession session, String id) throws SQLException;
	
	int deleteMember(SqlSession session, String id) throws SQLException;
	
	int updateMember(SqlSession session, MemberVO vo) throws SQLException;
	
	int insertMember(SqlSession session, MemberVO vo) throws SQLException;
	
}
