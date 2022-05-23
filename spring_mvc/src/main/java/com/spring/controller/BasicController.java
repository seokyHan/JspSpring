package com.spring.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BasicController {

	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() {		
		String url="test/main";
		
		return url;
		
	}
	
	//기존 서블릿 방식
	@RequestMapping(value="/test/param", method=RequestMethod.GET)
	public String testParam(HttpServletRequest request, HttpServletResponse response) {
		String url="test/main";
		
		String message = request.getParameter("message");
		
		request.setAttribute("message", message);
		
		return url;
		
	}
		
	//Model은  dispatcher servlet, adapter, ViewResolver, Controller에서 주소값으로 넘겨주고 받기때문에 다 같은걸 참조 
	@RequestMapping(value="/test2/param", method=RequestMethod.GET)
	public String test2Param(String message, Model model) {
		String url="test/main";
		
		model.addAttribute("message", message);
		
		return url;
		
	}
	
	//@ModelAttribute("message") Model까지 넣어주면서 타입까지 알아서 변환을 해줌
	@RequestMapping(value="/test3/param", method=RequestMethod.GET)
	public String test3Param(@ModelAttribute("message") String message) {
		String url="test/main";
		
		return url;
		
	}
	
	//맨위에거 아래처럼 간소화 return 값이 없으면 url그대로 줌
	@RequestMapping(value="/test/main", method=RequestMethod.GET)
	public void test4Param(@ModelAttribute("message") String message) { }
	
	//@RequestParam 넘어오는 parameter를 매핑해주는 녀석
	@RequestMapping(value="/test5/param", method=RequestMethod.GET)
	public String test5Param(@RequestParam(name="message", defaultValue="Have a nice day!!")
							String msg, Model model) {
		String url="test/main";
		
		model.addAttribute("message", msg);
		
		return url;	
	}
	
	@RequestMapping(value="/test6/param", method=RequestMethod.GET)
	public void test6Param(CommandObject command, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		out.println(command.a+":"+command.b+":"+command.c+":"+command.d+":"+command.k);
	}

}

class CommandObject{
	int a;
	String b;
	int c;
	Object d;
	boolean k;
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public Object getD() {
		return d;
	}
	public void setD(Object d) {
		this.d = d;
	}
	public boolean isK() {
		return k;
	}
	public void setK(boolean k) {
		this.k = k;
	}
	
}
