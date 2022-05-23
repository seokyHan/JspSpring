package com.spring.dao;

import java.sql.SQLException;

import com.jsp.dto.MemberVO;

public interface MemberDAO {
	
	MemberVO selectMemberById(String id) throws SQLException;
}
