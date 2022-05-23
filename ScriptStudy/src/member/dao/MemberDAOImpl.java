package member.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import member.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public List<MemberVO> selectMemberList(SqlSession session) throws SQLException {
		
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList");
	
		return memberList;
	}

}
