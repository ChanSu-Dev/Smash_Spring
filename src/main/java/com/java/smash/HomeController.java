package com.java.smash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.java.smash.dao.AdminDao;
import com.java.smash.dto.AdminDto;
import com.java.smash.dto.Medic;
import com.java.smash.util.Constant;

@Controller
@Repository
public class HomeController {
	
	public JdbcTemplate template;
	public HomeController() {
		System.out.println("tq");
	}
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		System.out.println("SetTemplate() called");
//		DataSource dataSource =null;
//		try {
//			Context context = new InitialContext();
//			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		try {
//			Connection connection = dataSource.getConnection();
//			PreparedStatement preparedStatement = null;
//			ResultSet resultSet = null;
//			String query = "select * from medic";
//			PreparedStatement pstmt = connection.prepareStatement(query);
//			resultSet = pstmt.executeQuery();
//			while(resultSet.next()) {
//				System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		dao가 없
		String query = "select * from medic;";
		ArrayList<Medic> medic = (ArrayList<Medic>) template.query(query, new BeanPropertyRowMapper<Medic>(Medic.class));
		for(Medic i : medic) {
			System.out.println(i.getName()+" "+i.getId());
		}
		this.template = template;
		Constant.template = this.template;
	}
	
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
