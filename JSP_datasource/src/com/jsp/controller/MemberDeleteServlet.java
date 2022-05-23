package com.jsp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.MemberDAO;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;


public class MemberDeleteServlet extends HttpServlet {

	private MemberService memberService;


	@Override
	public void init(ServletConfig config) throws ServletException {
			
		String memberServiceStr = config.getInitParameter("memberService");
	    String memberDAOStr = config.getInitParameter("memberDAO");
	    String sqlSessionFactoryStr = config.getInitParameter("sqlSessionFactory");

		
		
		try {
			
			
		    memberService = (MemberService)Class.forName(memberServiceStr).newInstance();
		    MemberDAO memberDAO =(MemberDAO)Class.forName(memberDAOStr).newInstance();
		    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)Class.forName(sqlSessionFactoryStr).newInstance();
	
			
			if (memberService instanceof MemberServiceImpl) {
				MemberServiceImpl memberServiceImpl = (MemberServiceImpl) memberService;
				memberServiceImpl.setSqlSessionFactory(sqlSessionFactory);
				memberServiceImpl.setMemberDAO(memberDAO);
				
				System.out.println("memberService injection clear!");
				
			}
		} catch (Exception e) {
			System.out.println("초기화 실패입니다.");
	         e.printStackTrace();

		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/WEB-INF/views/member/deleteResult.jsp";
		
		String resultstr="error";
		
		String id = request.getParameter("id");
		
		int result = 0;
		
		// 처리 부분
		
		
		try {
			result = memberService.removeMember(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		if(result > 0) {
			
			resultstr = "sucess";
		}
		
		request.setAttribute("resultstr", resultstr);
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
