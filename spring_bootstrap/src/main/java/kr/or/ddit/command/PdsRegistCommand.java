package kr.or.ddit.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jsp.dto.PdsVO;

public class PdsRegistCommand {
	
	private String writer;
	private String title;
	private String content;
	private List<MultipartFile> uploadFile;
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<MultipartFile> getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(List<MultipartFile> multi) {
		this.uploadFile = multi;
	}
	
	
	public PdsVO toPdsVO() {
		PdsVO pds = new PdsVO();
		pds.setWriter(this.writer);
		pds.setTitle(this.title);
		pds.setContent(this.content);
		
		return pds;
	}
	

}
