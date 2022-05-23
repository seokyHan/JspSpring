package kr.or.ddit.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jsp.dto.AttachVO;
import com.jsp.util.MakeFileName;

public class GetAttachesByMultipartFileAdapter {

	public static List<AttachVO> save(List<MultipartFile> multiFiles, String savePath) throws Exception {
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		if (multiFiles != null) {
			for (MultipartFile multi : multiFiles) {
				String fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
				File target = new File(savePath, fileName);
				
				target.mkdirs();
				
				multi.transferTo(target);
				
				AttachVO attach = new AttachVO();
				attach.setFileName(fileName);
				attach.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase());
				attach.setUploadPath(savePath);
				
				attachList.add(attach);
			}
		}
		
		return attachList;
	}
}