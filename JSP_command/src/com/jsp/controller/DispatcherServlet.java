package com.jsp.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private HandlerMapper handlerMapper;
  
	@Override
	public void init(ServletConfig config) throws ServletException {
		String handlerProperty = config.getInitParameter("urlProperty");
		try {
			if(handlerProperty != null && !handlerProperty.isEmpty()) {
				handlerMapper = new HandlerMapper(handlerProperty);
			}else {
				handlerMapper = new HandlerMapper();
			}
			System.out.println("[DispatcherServlet] handlerMapper가 준비되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[DispatcherServlet] handlerMapper가 준비되지 않았습니다");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// 사용자 URI 검출
		String command = request.getRequestURI(); // contextPath 포함.
		if(command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		}
		
		//commandHandler 실행(HandlerMapper 의로 handler 할당)
		Action action = null;
		String message = null;
		
		if(handlerMapper != null) { 
			action = handlerMapper.getAction(command);
			if(action != null) { 
				message = action.execute(request, response);
				System.out.println("message : " + message);
			}else {
				//올바른 요청 없다면 404에러
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		}else {
			// null이라면 500에러 (시스템에러)
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
}
