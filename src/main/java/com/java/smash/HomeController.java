package com.java.smash;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.smash.dao.AdminDao;
import com.java.smash.dto.AdminDto;

@Controller
public class HomeController {
	
//	public JdbcTemplate template;
	
//	@Autowired
//	public void setTemplate(JdbcTemplate template) {
//		Constant.template = this.template;
//	}
	private String adminId = null;
	private String adminPwd = null;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AdminDao.class);
		AdminDto conn = ctx.getBean("adminConfig", AdminDto.class);
		
		adminId = conn.getAdminId();
		adminPwd = conn.getAdminPw();
		
		return "home";
	}
	
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public String loginCheck(HttpServletRequest request, Model model) {
		String Uid = (String) request.getParameter("id");
		String Upwd = (String) request.getParameter("pwd");
		
		if (Uid.equals(adminId) && Upwd.equals(adminPwd)) {
			model.addAttribute("id", Uid);
			model.addAttribute("pwd", Upwd);
			return "/admin/admin";
		} 
		//else if() { db의 사용자와 일치하는 경우	
		//}
		else {	// 사용자와 관리자에 일치하지 않는경우
			return "redirect:home";
		}
		
	}
	
	
}
