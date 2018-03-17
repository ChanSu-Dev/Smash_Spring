package com.java.smash;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

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
	public String loginCheck(HttpServletRequest request, Model model, RedirectAttributes redirectAttr) {
		String id = (String) request.getParameter("id");
		String pwd = (String) request.getParameter("pwd");
		
		//FlashMap을 이용해서 redirect하면서 값을 넘김
		FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		fm.put("id", id);
		fm.put("pwd", pwd);
		
		if (id.equals(adminId) && pwd.equals(adminPwd)) {
			redirectAttr.addFlashAttribute("id", id).addFlashAttribute("pwd", pwd);
			return "redirect:admin/Main";
		} 
		//else if() { db의 사용자와 일치하는 경우	
		//}
		else {	// 사용자와 관리자에 일치하지 않는경우
			return "redirect:home";
		}
	}
	
	
	
	
}
