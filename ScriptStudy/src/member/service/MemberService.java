package member.service;

import java.sql.SQLException;
import java.util.List;

import member.dto.MemberVO;

public interface MemberService {
	
	List<MemberVO> getMemberList() throws SQLException;
}
