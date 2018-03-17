package com.java.smash;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("adminMain")
	public String adminMain(HttpSession session, HttpServletRequest request, Model model) {
		//FlashMap으로 넘어온 정보
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		
		if (map != null) {
			String id = (String) map.get("id");
			String pwd = (String) map.get("pwd");
			
			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
		}		
		return "admin/admin_main";
	}
	
	@RequestMapping(value = "adminDevice")
	public String adminDevice(HttpServletRequest request, Model model) {
		
		// db연결해서 row넘겨주기
		return "admin/admin_device";
	}
	
	@RequestMapping(value = "adminDoctor")
	public String adminDoctor(HttpServletRequest request, Model model) {
		
		// db연결해서 row넘겨주기
		return "admin/admin_doctor";
	}
	
	@RequestMapping(value = "adminConnection")
	public String adminConnection(HttpServletRequest request, Model model) {
		
		// db연결해서 row넘겨주기
		return "admin/admin_connection";
	}
}
