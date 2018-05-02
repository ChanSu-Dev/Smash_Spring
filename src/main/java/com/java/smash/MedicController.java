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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.java.smash.command.SCommand;
import com.java.smash.command.SConnectionListCommand;
import com.java.smash.command.SMedicChangepwdCommand;
import com.java.smash.command.SPatientAddCommand;
import com.java.smash.command.SPatientListCommand;
import com.java.smash.dto.MedicDto;
import com.java.smash.util.Constant;

@Controller
@RequestMapping("medic")
public class MedicController {

	public SCommand command;

	public JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		System.out.println("SetTemplate() admin called");

		String query = "select * from medic";
		ArrayList<MedicDto> medic = (ArrayList<MedicDto>) template.query(query,
				new BeanPropertyRowMapper<MedicDto>(MedicDto.class));

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
		command = new SPatientListCommand();
		command.execute(model);

		return "medic/medic_patient";
	}

	@RequestMapping("PatientAdd")
	public String medicPatientAdd(HttpServletRequest requset, Model model) {
		return "medic/medic_patient_add";
	}

	@RequestMapping(value = "PatientAddOK", method = RequestMethod.POST)
	public String medicPatientAddOk(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);

		command = new SPatientAddCommand();
		command.execute(model);

		return "redirect:Patient";
	}

	@RequestMapping(value = "Connection", method = RequestMethod.GET)
	public String medicDevice(HttpServletRequest request, Model model) {
		command = new SConnectionListCommand();
		command.execute(model);
		return "medic/medic_connection";
	}

	@RequestMapping(value = "Connection", method = RequestMethod.POST)
	public String medicDeviceOk(HttpServletRequest request, Model model) {
		System.out.println(request.getParameter("patientNumber"));
		System.out.println(request.getParameter("deviceNumber"));

		return "medic/medic_connection";
	}

	@RequestMapping("changepwd")
	public String medicChangepwd(HttpServletRequest request, Model model) {
		model.addAttribute("status", 0);

		return "medic/medic_changepwd";
	}

	@RequestMapping("changepwdCheck")
	public String medicChangepwdCheck(HttpSession session, HttpServletRequest request, Model model) {
		String current_id = (String) session.getAttribute("id");

		model.addAttribute("current_id", current_id);
		model.addAttribute("request", request);

		command = new SMedicChangepwdCommand();
		command.execute(model);

		return "redirect:ischangepwd";
	}

	@RequestMapping("ischangepwd")
	public String medicischangepwd(HttpServletRequest request, Model model) {
		String status = (String) request.getParameter("status");

		if (Integer.parseInt(status) == 0) {
			return "medic/medic_main";
		} else {
			model.addAttribute("status", Integer.parseInt(status));
			return "medic/medic_changepwd";
		}
	}
}
