package kr.or.ddit.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.dto.ReplyVO;
import com.jsp.service.ReplyService;

//url : /replies

//bno번 게시글의 페이징 처리된 댓글 목록
///replies/{bno}/{page} 			page list: GET방식
///replies 							regist : POST 방식 : 댓글 입력
///replies/{rno}					modify : PUT,PATCH방식, rno 댓글의 수정 
///replies/{bno}/{rno}/{page}		remove : DELETE 방식, rno 댓글의 삭제
///replies 		

// value=/board/{bno}, value=/board/{nno} 이렇게  같은 depth 에 있으면 adapter가 분간을 못하기때문에 
// method로 분간을 해줘야 한다. ex)첫번째꺼는 PATCH로 두번째거는 DELETE 이런식으로 아니면 depth로 분간으로 주던가  

@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService service;	
	
	@RequestMapping(method=RequestMethod.GET, value="/{bno}/{page}")
	public ResponseEntity<Map<String,Object>> replyList(@PathVariable("bno") int bno,
														@PathVariable("page") int page) throws Exception{
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		Criteria cri = new Criteria();
		cri.setPage(page);
		
	
		Map<String, Object> dataMap = service.getReplyList(bno, cri);
		entity = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		
		
		return entity;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody ReplyVO reply) throws Exception {
       
 		ResponseEntity<String> entity = null;
       
 	     reply.setReplytext(HTMLInputFilter.htmlSpecialChars(reply.getReplytext()));
 	      	    
          service.registReply(reply);
          
          Criteria cri = new Criteria();
          
          Map<String, Object> dataMap = service.getReplyList(reply.getBno(), cri);
          PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
          int realEndPage = pageMaker.getRealEndPage();
          
          entity = new ResponseEntity<String>(realEndPage + "", HttpStatus.OK);
       
       
       return entity;
    }

	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{rno}")
	public ResponseEntity<String> modify(@PathVariable("rno") int rno, @RequestBody ReplyVO reply) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		reply.setRno(rno);
		

		service.modifyReply(reply);
		entity = new ResponseEntity<String>(HttpStatus.OK);
	
		
		return entity;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{bno}/{rno}/{page}")
	public ResponseEntity<String> remove(@PathVariable("bno") int bno,
										 @PathVariable("rno") int rno,
										 @PathVariable("page") int page) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		service.removeReply(rno);
		
		Criteria cri = new Criteria();
		
		Map<String, Object> dataMap = service.getReplyList(bno, cri);
		PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
		
		int realEndPage = pageMaker.getRealEndPage();
		if(page > realEndPage) {
			page = realEndPage;
		}
		
		entity = new ResponseEntity<String>(""+page, HttpStatus.OK);
		
		
		return entity;
	}
	
}
