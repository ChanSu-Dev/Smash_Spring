package com.java.smash;

import java.util.Locale;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.smash.dao.AdminDao;
import com.java.smash.dto.ApplicationConfig;

@Controller
public class HomeController {
	
//	public JdbcTemplate template;
	
//	@Autowired
//	public void setTemplate(JdbcTemplate template) {
//		Constant.template = this.template;
//	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		AdminDao conn = ctx.getBean("adminConfig", AdminDao.class);
		
		System.out.println("ID : " + conn.getAdminId());
		System.out.println("PW : " + conn.getAdminPw());
		
		return "home";
	}
	
}
