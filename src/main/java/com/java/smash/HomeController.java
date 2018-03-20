package com.java.smash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.smash.dao.AdminDao;
import com.java.smash.dto.AdminDto;
import com.java.smash.dto.MedicDto;
import com.java.smash.dto.PatientDto;
import com.java.smash.util.Constant;

@Controller
@Repository
public class HomeController {

	public JdbcTemplate template;

	private String adminId = null;
	private String adminPwd = null;
	private String id = null;
	private String pwd = null;
	boolean isChecked = false;	//로그인 확인하는 변수
	ArrayList<MedicDto> medic = null;	//로그인 확인할 ArrayList

	HashMap<String, String> map = new HashMap<String, String>();

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		System.out.println("SetTemplate() Home called");

		String query = "select * from medic;";
		medic = (ArrayList<MedicDto>) template.query(query,
				new BeanPropertyRowMapper<MedicDto>(MedicDto.class));
		for (MedicDto i : medic) {
			System.out.println(i.getName() + " " + i.getId());
		}
		Constant.template = template;
	}

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
		id = (String) request.getParameter("id");
		pwd = (String) request.getParameter("pwd");
		
		for (MedicDto i : medic) {
			map.put(i.getId(), i.getPassword());
		}
		
		if (id.equals(adminId) && pwd.equals(adminPwd)) {		//관리자로 로그인할 경우
			redirectAttr.addFlashAttribute("id", id).addFlashAttribute("pwd", pwd);
			return "redirect:admin/Main";
		} else {			// 관리자가 아닌 경우
			for (String key : map.keySet()) {
				System.out.println(key +" : " + map.get(key));
				if (id.equals(key) && pwd.equals(map.get(key))) {
					isChecked = true;
					break;
				}
			}
			
			if (isChecked) {		//로그인 성공시에 medic으로 경로이동 
				redirectAttr.addFlashAttribute("id", id).addFlashAttribute("pwd", pwd);
				return "redirect:medic/Main";
			}else {				//로그인 실패시에 같은화면으로 경로이동
				return "redirect:home";
			}
		}
	}

}
