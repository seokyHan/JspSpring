package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.command.Criteria;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

import kr.or.ddit.command.PdsModifyCommand;
import kr.or.ddit.command.PdsRegistCommand;
import kr.or.ddit.util.GetAttachesByMultipartFileAdapter;

@Controller
@RequestMapping("/pds")
public class PdsController {

	@Resource(name = "pdsService")
	private PdsService service;

	@Resource(name = "fileUploadPath")
	private String fileUploadPath;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void main() throws Exception {}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Criteria cri, Model model) throws Exception {
			
		Map<String, Object> dataMap = service.getList(cri);
		model.addAttribute("dataMap", dataMap);
	}
	
	@RequestMapping(value = "/registForm", method = RequestMethod.GET)
	public void registForm() throws Exception {}
	
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String regist(PdsRegistCommand registCMD, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/list.do";
		
		// file저장 -> List<AttachVO>
		String savePath = this.fileUploadPath;
		List<AttachVO> attachList = GetAttachesByMultipartFileAdapter.save(registCMD.getUploadFile(), savePath);
		
		System.out.println();
		System.out.println(registCMD.getUploadFile());
		System.out.println();
		
		// DB저장
		PdsVO pds = registCMD.toPdsVO();
		pds.setAttachList(attachList);
		registCMD.setTitle((String) req.getAttribute("XSStitle"));
		service.regist(pds);
		
		// output
		rttr.addFlashAttribute("from", "regist");
		
		return url;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(int pno, String from, ModelAndView mnv) throws Exception {
		String url = "pds/detail";
		
		PdsVO pds = null;
		if (from != null && from.equals("list")) {
			pds = service.read(pno);
			url = "redirect:/pds/detail.do?pno=" + pno;
		} else {
			pds = service.getPds(pno);
		}
		
		// 파일명 재정의
		if (pds != null) {
			List<AttachVO> attachList = pds.getAttachList();
			if (attachList != null) {
				for (AttachVO attach : attachList) {
					String fileName = attach.getFileName().split("\\$\\$")[1];
					attach.setFileName(fileName);
				}
			}
		}
		
		mnv.addObject("pds", pds);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value = "/getFile", method = RequestMethod.GET, produces = "text/plain;charset:utf-8")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(int ano) throws Exception {
		
		ResponseEntity<byte[]> entity = null;

		AttachVO attach = service.getAttachByAno(ano);
		
		String fileName = attach.getFileName();
		String savedPath = this.fileUploadPath;
		
		InputStream in = null;
		try {
			in = new FileInputStream(new File(savedPath, fileName));
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			in.close();
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
	public ModelAndView modifyForm(int pno, ModelAndView mnv) throws Exception {
		String url = "pds/modifyForm";
		
		PdsVO pds = service.getPds(pno);
		
		// 파일명 재정의
		if (pds != null) {
			List<AttachVO> attachList = pds.getAttachList();
			if (attachList != null) {
				for (AttachVO attach : attachList) {
					String fileName = attach.getFileName().split("\\$\\$")[1];
					attach.setFileName(fileName);
				}
			}
		}
		
		mnv.addObject("pds", pds);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "text/plain;charset:utf-8")
	public String modify(PdsModifyCommand modifyCMD, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/detail.do";
		
		// 파일 삭제
		if (modifyCMD.getDeleteFile() != null && modifyCMD.getDeleteFile().length > 0) {
			for (String anoStr : modifyCMD.getDeleteFile()) {
				int ano = Integer.parseInt(anoStr);
				AttachVO attach = service.getAttachByAno(ano);
				
				File deleteFile = new File(attach.getUploadPath(), attach.getFileName());
				
				if (deleteFile.exists()) {
					deleteFile.delete();  // File삭제
				}
				service.removeAttachByAno(ano);  // DB삭제
			}
		}
		
		// 파일 저장
		List<AttachVO> attachList = GetAttachesByMultipartFileAdapter.save(modifyCMD.getUploadFile(), fileUploadPath);
		
		// PdsVO setting
		PdsVO pds = modifyCMD.toPdsVO();
		pds.setAttachList(attachList);
		pds.setTitle((String) req.getAttribute("XSStitle"));
		
		// DB 저장
		service.modify(pds);
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("pno", pds.getPno());
		
		return url;
	}
	
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(int pno, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/detail.do";
		
		// 파일 삭제
		List<AttachVO> attachList = service.getPds(pno).getAttachList();
		if (attachList != null) {
			for (AttachVO attach : attachList) {
				File target = new File(attach.getUploadPath(), attach.getFileName());
				if (target.exists()) {
					target.delete();
				}
			}
		}
		
		// DB 삭제
		service.remove(pno);
		
		rttr.addAttribute("pno", pno);
		rttr.addFlashAttribute("from", "remove");
		
		return url;
	}
	
	@RequestMapping("/getFile")
	public String getFile(int ano, Model model) throws Exception {
		
		String url = "downloadFile";
		
		AttachVO attach = service.getAttachByAno(ano);
		
		model.addAttribute("savedPath", attach.getUploadPath());
		model.addAttribute("fileName", attach.getFileName());
		
		return url;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
