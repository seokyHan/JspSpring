package kr.or.ddit.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.jsp.dto.MemberVO;
import com.jsp.util.GetUploadPath;

//파일에다가 로그를 기록해주는 녀석 경로필요
@Component("exceptionLoggerHelper")
public class ExceptionLoggerHelper {
	
	//프로퍼티에 있는경로 읽어야 하니까 빈등록 하고 읽어
	@Resource(name = "errorLogPath")
	private String errorLogPath;
	
	public void write(HttpServletRequest request, Exception e, String classType) {
		
		String savePath = errorLogPath.replace("/", File.separator);
		String logFilePath = savePath + File.separator + "system_exception_log.csv";
		
		String url = request.getRequestURI().replace(request.getContextPath(), "");
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
		String loginUserName = ((MemberVO) request.getSession().getAttribute("loginUser")).getName();
		String exceptionType = e.getClass().getName();
		
		String log = "[Error : " + exceptionType + "]" + url + "," + date + "," + loginUserName + "," + classType + "\n";
		
		//로그파일 생성
		File file = new File(savePath);
		file.mkdirs();
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(logFilePath,true));
			
			//로그 기록
			out.write(log);
			out.newLine();
			out.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
