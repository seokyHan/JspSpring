package kr.or.ddit.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jsp.dto.MemberVO;

//advisor는 컨트롤러내에서 에러처리할때 사용하고(공통으로 사용하는곳)
//interceptor는 특정 컨틀롤러나 url에서 처리할때 사용. 결론은 둘다 할 수는 있다...
@ControllerAdvice
public class ExceptionControllerAdvisor {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvisor.class);
	
	@ExceptionHandler(SQLException.class)
	public String sqlExceptionPage(Exception e, Model model, HttpSession session) throws SQLException{
		String url = "error/sqlException";
		
		logger.error(e.toString());
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		model.addAttribute("exception", e);
		model.addAttribute("user", loginUser!=null ? loginUser.getName()+"님" : "");
		
		return url;
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionPage(Exception e, Model model, HttpSession session) throws SQLException{
		String url = "error/500";
		
		logger.error(e.toString());
		
		return url;
	}
	
}
