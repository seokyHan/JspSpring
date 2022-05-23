package com.jsp.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/root-context.xml")
public class TestMemberServiceImpl {

	@Resource(name = "searchMemberService") //bean id 로 비교
	SearchMemberService searchMemberService;
	
	@Test
	public void testGetList() throws Exception{
		Criteria cri = new Criteria();
		
		List<MemberVO> memberList = (List<MemberVO>) searchMemberService.getSearchMemberList(cri).get("memberList");
		
		Assert.assertEquals(9, memberList.size());
	}
	
}
