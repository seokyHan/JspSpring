package com.spring.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RedirectController {
	
	@RequestMapping(value="/a")
	public String a(RedirectAttributes rttr) {
		String url = "redirect:receive";
		
//		rttr.addAttribute("name","kim");
//		rttr.addAttribute("message", "good Day!");
		
		//요청해서 올때 attribute를 한번만 심어주게 하기위해
		rttr.addFlashAttribute("name","kim");
		
		return url;
	}
	
	@RequestMapping(value="/receive")
	public void receive(String name, Model model) {
//		model.addAttribute("name", name);
	}
	
}
