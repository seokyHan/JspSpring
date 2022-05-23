package com.jsp.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.MemberRegistCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberRegistAction implements Action {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "member/regist_success";
		
		try {
			MemberRegistCommand memberRequest = 
					(MemberRegistCommand) HttpRequestParameterAdapter.execute(request, MemberRegistCommand.class);
			
			MemberVO member = memberRequest.toMemberVO();
			System.out.println("action   :   " + member.getPicture());
			memberService.regist(member);
			request.setAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}

}
