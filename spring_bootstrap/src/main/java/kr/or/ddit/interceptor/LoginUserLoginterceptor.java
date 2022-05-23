package kr.or.ddit.interceptor;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jsp.dto.MemberVO;

public class LoginUserLoginterceptor extends HandlerInterceptorAdapter {
	
	//security는 dispatcher servlet 안거치기때문에 여기서 interceptor하면 안돼~ loginSuccessHandler에서 해야지~
	private String savePath = "c:\\log";
	private String saveFileName = "login_user_log.csv";
	
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		MemberVO loginUser = (MemberVO) request.getSession().getAttribute("loginUser");
		
		if(loginUser == null) {
			return;
		}
		
		//로그인 정보를 스트링으로 저장.
		String tag = "[login:user]";
		String log = tag 
					+ loginUser.getId()+","
					+ loginUser.getPhone()+","
					+ loginUser.getEmail()+","
					+ request.getRemoteAddr()+","
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		File file = new File(savePath);
		file.mkdirs();
		
		String logFilePath = savePath + File.separator + saveFileName;
		// default값 false true로 해야 이어서 씀 false는 지우고 새로 작성함
		BufferedWriter out = new BufferedWriter(new FileWriter(logFilePath, true));
		
		// 로그 기록
		out.write(log);
		out.newLine();
		out.close();
	}
	
	
	
	

}
