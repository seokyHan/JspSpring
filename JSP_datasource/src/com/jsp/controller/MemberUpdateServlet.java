package com.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;

/**
 * Servlet implementation class memberUpdateServlet
 */
public class MemberUpdateServlet extends HttpServlet {

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
		// TODO Auto-generated method stub
		
		

	

		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		int result = 0;
		String resultstr="error";
		String url = "/WEB-INF/views/member/updateReuslt.jsp";
		
		MemberVO vo = new MemberVO();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String adress = request.getParameter("addr");
		
		System.out.println(id);
		
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setPhone(phone);
		vo.setEmail(email);
		vo.setAddress(adress);
		
		// 처리 부분
		
		try {
			result = memberService.modifyMember(vo);
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

}
