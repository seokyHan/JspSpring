package member.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import member.dto.MemberVO;

public interface MemberDAO {
	
	List<MemberVO> selectMemberList(SqlSession session) throws SQLException;

}
