package com.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int dan, gopp;
		String output = "";
		int target;
		
		dan = Integer.parseInt(request.getParameter("dan"));
		gopp = Integer.parseInt(request.getParameter("gop"));
		target = Integer.parseInt(request.getParameter("target"));
		
		for(; dan < target; dan++) {
				output += dan + "단" + "<br>";
			for(int gop=1; gop < gopp; gop++) {
				output += dan + " X " + gop + " = " + dan*gop + "<br>";
			}
			output += "<br>";
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(output);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
