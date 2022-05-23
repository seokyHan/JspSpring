package com.jsp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class TestListener implements ServletContextListener {
	// 사용자 요청없이도 무조건 실행시켜야 할때가 있다. 그때 listener 등록. xml에 리스너느 한개밖에 없음
	// filter는 get, post 관계없이 사용자 요청(request)가 올때마다 서블릿마다 등록하기 번거로우니까 등록 리스너와 관점이 다름.
    public void contextDestroyed(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    }

	
    public void contextInitialized(ServletContextEvent event)  {
    	String message = event.getServletContext().getInitParameter("message");
        System.out.println("TestListener loading...");
        System.out.println(message);
    }
	
}
