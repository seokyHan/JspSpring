package com.spring.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {

	@RequestMapping("/fileUploadForm")
	public void fileUpload() {}
	
	@RequestMapping(method=RequestMethod.POST, value="/multipartFile", produces="text/plain;charset=utf-8")
	public ModelAndView uploadByMultipartFile(@RequestParam(defaultValue="제목없음") String title,
											  @RequestParam("file") MultipartFile multi,
											  ModelAndView mnv) throws Exception{
		//파일 저장 폴더설정
		String uploadPath = "c:\\upload\\file";
		
		//파일명 중복 방지
		String fileName = multi.getOriginalFilename();
		
		//파일 경로확인 및 생성
		File file = new File(uploadPath, fileName);
		file.mkdirs();
		
		//파일 저장
		multi.transferTo(file);
		
		mnv.addObject("title",title);
		mnv.addObject("originalFileName", multi.getOriginalFilename());
		mnv.addObject("uploadedFileName", file.getName());
		mnv.addObject("uploadPath", file.getAbsolutePath());
		mnv.setViewName("fileUploaded");
				
		return mnv;
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/multipartHttpServletRequest")
	public ModelAndView uploadByMultipartHttpServletRequest(MultipartHttpServletRequest request, ModelAndView mnv) throws Exception{
		
		String title = request.getParameter("title");
		MultipartFile multi = request.getFile("file");
		
		mnv = uploadByMultipartFile(title, multi, mnv);
				
		return mnv;

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/commandObject")
	public ModelAndView uploadByCommandObject(MultiPartcommand command, ModelAndView mnv) throws Exception{
		String title = command.getTitle();
		MultipartFile multi = command.getFile();
		
		mnv = uploadByMultipartFile(title, multi, mnv);
				
		return mnv;
	}
}

class MultiPartcommand{
	
	private String title;
	private MultipartFile file;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
