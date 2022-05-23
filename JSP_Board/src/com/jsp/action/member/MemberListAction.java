package com.jsp.action.member;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.Criteria;

import com.jsp.service.MemberService;

public class MemberListAction implements Action {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "member/list";
		
		String pageParam = request.getParameter("page");
		String perPageNumParam = request.getParameter("perPageNum");
		String searchTypeParam = request.getParameter("searchType");
		String keywordParam = request.getParameter("keyword");
		
		Criteria cri = new Criteria();
		cri.setSearchType(searchTypeParam);
		cri.setKeyword(keywordParam);
		
		try {
			if(pageParam !=null && !pageParam.isEmpty()) cri.setPage(Integer.parseInt(pageParam));
			if(perPageNumParam != null && !perPageNumParam.isEmpty()) cri.setPerPageNum(Integer.parseInt(perPageNumParam));
		}catch(Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
				
		request.setAttribute("cri", cri);
		
		try {
			
			Map<String,Object> dataMap = memberService.getSearchMemberList(cri);
			request.setAttribute("dataMap", dataMap);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return url;

	}

}
