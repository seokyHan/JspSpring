package kr.or.ddit.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:kr/or/ddit/context/root-context.xml")
@Transactional
public class TestMemberDAOBeanImpl {
	
	@Autowired
	private MemberDAOBean memberDAOBean;
	
	@Test
	public void testSelectMember()throws Exception{
		String id = "mimi";
		MemberVO member = memberDAOBean.selectMemberById(id);
		
		Assert.assertEquals(id, member.getId());
	}
}
