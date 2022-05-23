package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.MemberVO;

public interface MemberService {

	// 회원정보 조회
	
	List<MemberVO> getMemberList() throws SQLException;
	
	MemberVO getMemberDetail(String id) throws SQLException;
	
	int addMember(MemberVO vo) throws SQLException;
	
	int modifyMember(MemberVO vo) throws SQLException;
	
	int removeMember(String id) throws SQLException;
	
}
