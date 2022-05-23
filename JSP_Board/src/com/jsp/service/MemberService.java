package com.jsp.service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;
import com.jsp.exception.IdNotFoundException;
import com.jsp.exception.InvalidPasswordException;

public interface MemberService {
	
	// 회원검색(페이징처리)
	public List<MemberVO> getMemberList(Criteria cri)throws Exception;
	
	// 분류별 회원 검색(정렬포함)
	Map<String,Object> getSearchMemberList(Criteria cri)throws Exception;
	
	// 회원 로그인
	public void login(String id, String pwd)throws IdNotFoundException, InvalidPasswordException, SQLException;
	
	public MemberVO getMember(String id) throws Exception;
	
	// 회원등록
	public void regist(MemberVO member) throws Exception;
	
	// 회원수정
	void modify(MemberVO member) throws Exception;
	
	// 회원삭제
	void remove(String id) throws Exception;
	
	// 회원 상태변경
	void enabled(String id, int enabled)throws Exception;

}
