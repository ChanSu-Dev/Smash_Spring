package com.java.smash.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.java.smash.dao.IDeviceDao;
import com.java.smash.dao.IMedicDao;
import com.java.smash.util.Constant;

@Controller
@RequestMapping("/admin")
public class AdminController {

	String query = null;
	String PdeviceNo = null;
	String PmedicNo = null;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		Constant.template = template;
	}

	@Autowired
	private SqlSession sqlSession;

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

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		model.addAttribute("list", dao.deviceList());

		return "admin/admin_device";
	}

	@RequestMapping("DeviceAdd")
	public String adminDeviceAdd(HttpServletRequest request, Model model) {

		return "admin/admin_device_add";
	}

	@RequestMapping(value = "DeviceAddOk", method = RequestMethod.POST)
	public String adminDeviceAddOk(HttpServletRequest request, Model model) {

		String deviceNo = request.getParameter("deviceNumber");
		String sort = request.getParameter("sort");
		String version = request.getParameter("version");
		String ipv4 = request.getParameter("ipv4_address");
		String ipv6 = request.getParameter("ipv6_address");
		String place = request.getParameter("place");

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		dao.deviceInsert(deviceNo, version, sort, ipv4, ipv6, place);

		return "redirect:Device";
	}

	@RequestMapping("DeviceEdit")
	public String adminDeviceEdit(HttpServletRequest request, Model model) {
		PdeviceNo = request.getParameter("deviceNumber");

		String deviceNum = request.getParameter("deviceNumber");

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		model.addAttribute("list", dao.deviceEdit(deviceNum));

		return "admin/admin_device_edit";
	}

	@RequestMapping("DeviceEditOk")
	public String adminDeviceEditOk(HttpServletRequest request, Model model) {

		String deviceNo = request.getParameter("deviceNumber");
		String sort = request.getParameter("sort");
		String version = request.getParameter("version");
		String ipv4 = request.getParameter("ipv4_address");
		String ipv6 = request.getParameter("ipv6_address");
		String place = request.getParameter("place");

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		dao.deviceEditOk(deviceNo, version, sort, ipv4, ipv6, place, PdeviceNo);

		return "redirect:Device";
	}

	@RequestMapping("DeviceDelete")
	public String adminDeviceDelete(HttpServletRequest request, Model model) {
		String deviceNum = request.getParameter("deviceNumber");

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		dao.deviceDelete(deviceNum);

		return "redirect:Device";
	}

	@RequestMapping("Medic")
	public String adminDoctor(HttpServletRequest request, Model model) {

		IMedicDao dao = sqlSession.getMapper(IMedicDao.class);
		model.addAttribute("list", dao.medicList());

		return "admin/admin_medic";
	}

	@RequestMapping("MedicAdd")
	public String adminDoctorAdd(HttpServletRequest request, Model model) {

		return "admin/admin_medic_add";
	}

	@RequestMapping(value = "MedicAddOk", method = RequestMethod.POST)
	public String adminDoctorAddOk(HttpServletRequest request, Model model) {

		String employeeNumber = request.getParameter("employeeNumber");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String belong = request.getParameter("belong");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");
		String birth = request.getParameter("birth");
		
		IMedicDao dao = sqlSession.getMapper(IMedicDao.class);
		dao.medicInsert(employeeNumber, id, password, name, belong, contact, address, birth);

		return "redirect:Medic";
	}

	@RequestMapping("MedicEdit")
	public String adminMediceEdit(HttpServletRequest request, Model model) {
		String eNo = request.getParameter("employeeNumber");
		PmedicNo = eNo;

		IMedicDao dao = sqlSession.getMapper(IMedicDao.class);
		model.addAttribute("list", dao.medicEdit(eNo));

		return "admin/admin_medic_edit";
	}

	@RequestMapping("MedicEditOk")
	public String adminMedicEditOk(HttpServletRequest request, Model model) {

		String employeeNumber = request.getParameter("employeeNumber");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String belong = request.getParameter("belong");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");
		String birth = request.getParameter("birth");

		IMedicDao dao = sqlSession.getMapper(IMedicDao.class);
		dao.medicEditOk(employeeNumber, name, id, password, belong, contact, address, birth, PmedicNo);

		return "redirect:Medic";
	}

	@RequestMapping("MedicDelete")
	public String adminMedicDelete(HttpServletRequest request, Model model) {

		String eNo = request.getParameter("employeeNumber");

		IMedicDao dao = sqlSession.getMapper(IMedicDao.class);
		dao.medicDelete(eNo);

		return "redirect:Medic";
	}

	@RequestMapping("Connection")
	public String adminConnection(HttpServletRequest request, Model model) {

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		model.addAttribute("list", dao.adminDeviceList());

		return "admin/admin_connection";
	}

	@RequestMapping(value="ConnectionStart", method = RequestMethod.POST)
	public String adminConnectionStart(HttpServletRequest request, Model model) {

		String deviceNumber = request.getParameter("deviceNumber");

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		dao.adminConnectionStartDao(deviceNumber);

		return "redirect:Connection";
	}

	@RequestMapping("ConnectionStop")
	public String adminConnectionStop(HttpServletRequest request, Model model) {

		String deviceNumber = request.getParameter("deviceNumber");

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		dao.connectionStopDao(deviceNumber);

		return "redirect:Connection";
	}
}
