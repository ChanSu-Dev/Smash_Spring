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
import com.java.smash.command.SConnectionListCommand;
import com.java.smash.command.SConnectionQueryCommand;
import com.java.smash.command.SDeviceAddCommand;
import com.java.smash.command.SDeviceDeleteCommand;
import com.java.smash.command.SDeviceEditCommand;
import com.java.smash.command.SDeviceEditOkCommand;
import com.java.smash.command.SDeviceListCommand;
import com.java.smash.command.SMedicAddCommand;
import com.java.smash.command.SMedicDeleteCommand;
import com.java.smash.command.SMedicListCommand;
import com.java.smash.command.SMediceEditCommand;
import com.java.smash.command.SMediceEditOkCommand;
import com.java.smash.dto.MedicDto;
import com.java.smash.util.Constant;

@Controller
@RequestMapping("/admin")
public class AdminController {

	SCommand command = null;
	String query = null;
	String PdeviceNo = null;
	String PmedicNo = null;

	public JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		query = "select * from medic";
		ArrayList<MedicDto> medic = (ArrayList<MedicDto>) template.query(query,
				new BeanPropertyRowMapper<MedicDto>(MedicDto.class));
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

	@RequestMapping("DeviceAdd")
	public String adminDeviceAdd(HttpServletRequest request, Model model) {

		return "admin/admin_device_add";
	}

	@RequestMapping("DeviceAddOk")
	public String adminDeviceAddOk(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);

		command = new SDeviceAddCommand();
		command.execute(model);
		return "redirect:Device";
	}

	@RequestMapping("DeviceEdit")
	public String adminDeviceEdit(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		PdeviceNo = request.getParameter("deviceNumber");

		command = new SDeviceEditCommand();
		command.execute(model);

		return "admin/admin_device_edit";
	}

	@RequestMapping("DeviceEditOk")
	public String adminDeviceEditOk(HttpServletRequest request, Model model) {
		model.addAttribute("PdeviceNo", PdeviceNo);
		model.addAttribute("request", request);

		command = new SDeviceEditOkCommand();
		command.execute(model);

		return "redirect:Device";
	}

	@RequestMapping("DeviceDelete")
	public String adminDeviceDelete(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);

		command = new SDeviceDeleteCommand();
		command.execute(model);
		return "redirect:Device";
	}

	@RequestMapping("Medic")
	public String adminDoctor(HttpServletRequest request, Model model) {
		command = new SMedicListCommand();
		command.execute(model);

		return "admin/admin_medic";
	}

	@RequestMapping("MedicAdd")
	public String adminDoctorAdd(HttpServletRequest request, Model model) {

		return "admin/admin_medic_add";
	}

	@RequestMapping("MedicAddOk")
	public String adminDoctorAddOk(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);

		command = new SMedicAddCommand();
		command.execute(model);
		return "redirect:Medic";
	}

	@RequestMapping("MedicEdit")
	public String adminMediceEdit(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		PmedicNo = request.getParameter("employeeNumber");

		command = new SMediceEditCommand();
		command.execute(model);

		return "admin/admin_medic_edit";
	}

	@RequestMapping("MedicEditOk")
	public String adminMedicEditOk(HttpServletRequest request, Model model) {
		model.addAttribute("PmedicNo", PmedicNo);
		model.addAttribute("request", request);

		command = new SMediceEditOkCommand();
		command.execute(model);

		return "redirect:Medic";
	}

	@RequestMapping("MedicDelete")
	public String adminMedicDelete(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);

		command = new SMedicDeleteCommand();
		command.execute(model);
		return "redirect:Medic";
	}

	@RequestMapping("Connection")
	public String adminConnection(HttpServletRequest request, Model model) {
		command = new SConnectionListCommand();
		command.execute(model);

		return "admin/admin_connection";
	}

	@RequestMapping("ConnectionStart")
	public String adminConnectionStart(HttpServletRequest request, Model model) {
		model.addAttribute("status", "1");
		model.addAttribute("deviceNumber", request.getParameter("deviceNumber"));

		command = new SConnectionQueryCommand();
		command.execute(model);
		return "redirect:Connection";
	}

	@RequestMapping("ConnectionStop")
	public String adminConnectionStop(HttpServletRequest request, Model model) {
		model.addAttribute("status", "0");
		model.addAttribute("deviceNumber", request.getParameter("deviceNumber"));

		command = new SConnectionQueryCommand();
		command.execute(model);
		return "redirect:Connection";
	}
}
