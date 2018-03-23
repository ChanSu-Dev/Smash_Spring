package com.java.smash;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.java.smash.command.SCommand;
import com.java.smash.command.SDeviceListCommand;
import com.java.smash.command.SMedicListCommand;
import com.java.smash.dto.MedicDto;
import com.java.smash.util.Constant;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	SCommand command = null;
	
	public JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		String query = "select * from medic";
		ArrayList<MedicDto> medic = (ArrayList<MedicDto>) template.query(query,
				new BeanPropertyRowMapper<MedicDto>(MedicDto.class));
		for (MedicDto i : medic) {
			System.out.println(i.getName() + " :::: " + i.getId());
		}
		Constant.template = template;
	}

	@RequestMapping("Main")
	public String adminMain(HttpSession session, HttpServletRequest request, Model model) {
		// FlashMap으로 넘어온 정보
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);

		if (map != null) {
			String id = (String) map.get("id");
			String pwd = (String) map.get("pwd");

			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
		}
		return "admin/admin_main";
	}

	@RequestMapping(value = "Device")
	public String adminDevice(HttpServletRequest request, Model model) {
		command = new SDeviceListCommand();
		command.execute(model);
		
		return "admin/admin_device";
	}

	@RequestMapping(value = "Medic")
	public String adminDoctor(HttpServletRequest request, Model model) {
		command = new SMedicListCommand();
		command.execute(model);

		return "admin/admin_medic";
	}

	@RequestMapping(value = "Connection")
	public String adminConnection(HttpServletRequest request, Model model) {

		// db연결해서 row넘겨주기
		return "admin/admin_connection";
	}

	@RequestMapping(value = "DeviceAdd")
	public String adminDeviceAdd(HttpServletRequest request, Model model) {

		// db연결해서 row넘겨주기
		return "admin/admin_device_add";
	}

	@RequestMapping(value = "MedicAdd")
	public String adminDoctorAdd(HttpServletRequest request, Model model) {

		// db연결해서 row넘겨주기
		return "admin/admin_medic_add";
	}
}
