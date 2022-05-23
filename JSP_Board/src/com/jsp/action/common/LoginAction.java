package com.jsp.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.exception.IdNotFoundException;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.service.MemberService;

public class LoginAction implements Action{
	
	private MemberService memberService;
	public void setMemberServcie(MemberService memberService) {
		this.memberService = memberService;
	}
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url = "redirect:index.do";
		
		//입력
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		try {
			memberService.login(id, pwd);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", memberService.getMember(id));
			session.setMaxInactiveInterval(6*60);
		} catch (IdNotFoundException | InvalidPasswordException e) {
			request.setAttribute("message", e.getMessage());
			url = "common/login_fail";
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
				
		
		return url;
	}

}
