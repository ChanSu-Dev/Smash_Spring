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

import com.java.smash.dto.MedicDto;
import com.java.smash.util.Constant;

@Controller
@RequestMapping("medic")
public class MedicController {
	
	public JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		System.out.println("SetTemplate() admin called");

		String query = "select * from medic";
		ArrayList<MedicDto> medic = (ArrayList<MedicDto>) template.query(query,
				new BeanPropertyRowMapper<MedicDto>(MedicDto.class));
		for (MedicDto i : medic) {
			System.out.println(i.getName() + " :::: " + i.getId());
		}
		Constant.template = template;
	}
	
	@RequestMapping("Main")
	public String medicMain(HttpSession session, HttpServletRequest request, Model model) {
		// FlashMap으로 넘어온 정보
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);

		if (map != null) {
			String id = (String) map.get("id");
			String pwd = (String) map.get("pwd");

			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
		}
		return "medic/medic_main";
	}
	
	@RequestMapping("Patient")
	public String medicPatient(HttpServletRequest request, Model model) {
		return "medic/medic_patient";
	}
	
	@RequestMapping("PatientAdd")
	public String medicPatientAdd(HttpServletRequest requset, Model model) {
		return "medic/medic_patient_add";
	}
	
	@RequestMapping("Device")
	public String medicDevice(HttpServletRequest requestm, Model modle) {
		return "medic/medic_device";
	}
}
