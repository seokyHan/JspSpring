package com.spring.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RESTController {
	
	private Map<String,Object> dataMap = new HashMap<>();
	
	{
		dataMap.put("abc","123");
		dataMap.put("ㄱㄴㄷ","123");
		
		Map<String,String> tempMap = new HashMap<>();
		tempMap.put("a","1");
		tempMap.put("b","2");
		tempMap.put("c","3");
		tempMap.put("d","4");
		
		dataMap.put("temp",tempMap);
	}
	
//	@RequestMapping(method=RequestMethod.GET, value="/test/old")
//	public void restOld(HttpServletResponse response) throws Exception{
//		
//		ObjectMapper mapper = new ObjectMapper();
//
//		response.setContentType("application/json;charset=utf-8");
//		PrintWriter out = response.getWriter();
//
//		out.println(mapper.writeValueAsString(dataMap));
//
//		out.close();
//		
//	}
	
	//위에거 간단하게 만든거
	@RequestMapping(method=RequestMethod.GET, value="/rest/spring")
	//@ResponseBody는 String이 아닌 객체 형태가 오면 위의 작업(jackson databind)을 adapter가 변환해줘서 body에 심음   
	//@ResponseBody 위에 Controller를 RestController로 하면 @ResponseBody를 안써줘도 됨
	public Map<String, Object> restSpring(){
		return dataMap;
	}
	
	//에러처리(200 방지)
	@RequestMapping(method=RequestMethod.GET, value="/rest/best")
	//@ResponseBody
	public ResponseEntity<Map<String, Object>> restSpringBest(){
		ResponseEntity<Map<String, Object>> result = null;
		
		try {
			
			result = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}

}
