package com.jsp.action.member;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.util.GetUploadPath;

public class MemberRemoveAction implements Action{
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "member/remove_success";
		String id = request.getParameter("id");
		MemberVO member = memberService.getMember(id);
		
		String savedPath = GetUploadPath.getUploadPath("member.picture.upload");
		String picture = member.getPicture();
		File deleteFile = new File(savedPath, picture);
		if(deleteFile.exists()) {
			deleteFile.delete();
		}
		
		memberService.remove(id);
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(loginUser != null && member.getId().equals(loginUser.getId())) {
			session.invalidate();
		}
		
		request.setAttribute("member", member);
		
		
		return url;
	}

}
