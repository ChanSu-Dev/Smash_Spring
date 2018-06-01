package com.java.smash.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.java.smash.dao.IDeviceDao;
import com.java.smash.dao.IMedicDao;
import com.java.smash.dao.IPatientDao;
import com.java.smash.dao.IProgramDao;
import com.java.smash.dto.MedicDto;
import com.java.smash.dto.PatientDto;
import com.java.smash.util.Constant;

@Controller
@RequestMapping("medic")
public class MedicController {

	public JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		Constant.template = template;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
		return multipartResolver;
	}

	@Autowired
	private SqlSession sqlSession;

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

		IPatientDao dao = sqlSession.getMapper(IPatientDao.class);
		model.addAttribute("list", dao.patientList());

		return "medic/medic_patient";
	}

	@RequestMapping("PatientAdd")
	public String medicPatientAdd(HttpServletRequest requset, Model model) {

		IProgramDao dao = sqlSession.getMapper(IProgramDao.class);
		model.addAttribute("list", dao.programList());

		return "medic/medic_patient_add";
	}

	@RequestMapping(value = "PatientAddOK", method = RequestMethod.POST)
	public String medicPatientAddOk(HttpServletRequest request, Model model) {

		String patientNumber = request.getParameter("patientNumber");
		String patientName = request.getParameter("patientName");
		String patientDisease = request.getParameter("patientDisease");
		String patientStatus = request.getParameter("patientStatus");
		String patientProgram_1 = request.getParameter("program_1");
		String patientProgram_2 = request.getParameter("program_2");
		String patientProgram_3 = request.getParameter("program_3");
		String patientProgram_4 = request.getParameter("program_4");
		String patientProgram_5 = request.getParameter("program_5");

		IPatientDao dao = sqlSession.getMapper(IPatientDao.class);
		dao.patientInsert(patientNumber, patientName, patientDisease, patientStatus, patientProgram_1, patientProgram_2,
				patientProgram_3, patientProgram_4, patientProgram_5);

		return "redirect:Patient";
	}

	@RequestMapping("PatientEdit")
	public String medicPatientEdit(HttpServletRequest request, Model model) {

		String patientNumber = request.getParameter("patientNumber");

		IProgramDao Pdao = sqlSession.getMapper(IProgramDao.class);
		model.addAttribute("Plist", Pdao.programList());

		IPatientDao dao = sqlSession.getMapper(IPatientDao.class);
		model.addAttribute("list", dao.patientEditList(patientNumber));
		// dao.updateDao(dtos.get(1), )

		return "medic/medic_patient_edit";
	}

	@RequestMapping(value = "PatientEditOk", method = RequestMethod.POST)
	public String medicPatientEditOk(HttpServletRequest request, Model model) {

		String patientNumber = request.getParameter("patientNumber");
		String patientName = request.getParameter("patientName");
		String patientDisease = request.getParameter("patientDisease");
		String patientStatus = request.getParameter("patientStatus");
		String patientProgram_1 = request.getParameter("program_1");
		String patientProgram_2 = request.getParameter("program_2");
		String patientProgram_3 = request.getParameter("program_3");
		String patientProgram_4 = request.getParameter("program_4");
		String patientProgram_5 = request.getParameter("program_5");
		
		IPatientDao dao = sqlSession.getMapper(IPatientDao.class);
		dao.patientUpdate(patientName, patientDisease, patientStatus, patientProgram_1, patientProgram_2,
				patientProgram_3, patientProgram_4, patientProgram_5, patientNumber);
		return "redirect:Patient";
	}

	@RequestMapping("PatientDelete")
	public String medicPatientDelete(HttpServletRequest request, Model model) {

		String patientNumber = request.getParameter("patientNumber");

		IPatientDao dao = sqlSession.getMapper(IPatientDao.class);
		dao.patientDelete(patientNumber);

		return "redirect:Patient";
	}

	@RequestMapping(value = "PatientInfo")
	public String medicPatientInfo(HttpServletRequest request, Model model) {

		String pNum = request.getParameter("pNum");

		IPatientDao patientDao = sqlSession.getMapper(IPatientDao.class);
		ArrayList<PatientDto> patientList = patientDao.patientEditList(pNum);
		model.addAttribute("patientList", patientList);

		IDeviceDao deviceNum = sqlSession.getMapper(IDeviceDao.class);
		try {
			String dno = deviceNum.getPatientDevice(pNum);
			model.addAttribute("deviceNum", dno);
		} catch (Exception e) {
			model.addAttribute("deviceNum", "NULL");
		}

		IProgramDao programList = sqlSession.getMapper(IProgramDao.class);
		for (int i = 1; i < 4; i++) { // 프로그램 이름 넘기
			model.addAttribute("program_" + i, programList.getProgramName(i, pNum));
		}

		return "medic/medic_patient_info";
	}

	@RequestMapping(value = "Connection", method = RequestMethod.GET)
	public String medicConnection(HttpServletRequest request, Model model) {

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		model.addAttribute("list", dao.deviceList());

		return "medic/medic_connection";
	}

	@RequestMapping(value = "ConnectionStart", method = RequestMethod.POST)
	public String medicConnectionStart(HttpServletRequest request, Model model) {

		String patientNumber = request.getParameter("patientNumber");
		String deviceNumber = request.getParameter("deviceNumber");

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		dao.connectionStartDao(patientNumber, deviceNumber);

		return "redirect:Connection";
	}

	@RequestMapping(value = "ConnectionStop", method = RequestMethod.POST)
	public String medicConnectionStop(HttpServletRequest request, Model model) {

		String deviceNumber = request.getParameter("deviceNumber");

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		dao.connectionStopDao(deviceNumber);

		return "redirect:Connection";
	}

	@RequestMapping("Program")
	public String medicProgram(HttpServletRequest request, Model model) {

		IProgramDao dao = sqlSession.getMapper(IProgramDao.class);
		model.addAttribute("list", dao.programList());

		return "medic/medic_program";
	}

	@RequestMapping("ProgramAdd")
	public String medicProgramAdd(HttpServletRequest requset, Model model) {

		return "medic/medic_program_add";
	}

	@RequestMapping(value = "ProgramAddOK", method = RequestMethod.POST)
	public String medicProgramAddOk(@RequestParam("selectImage") MultipartFile file, HttpServletRequest request,
			Model model) {

		String programNumber = request.getParameter("programNumber");
		String programName = request.getParameter("programName");
		String programContent = request.getParameter("programContent");
		String programDisease = request.getParameter("programDisease");

		IProgramDao dao = sqlSession.getMapper(IProgramDao.class);
		dao.programInsert(programNumber, programName, programContent, programDisease);

		// 파일 이름 변경
		String getFileName[] = file.getOriginalFilename().split("\\.");
		String reName = "programImg_" + programNumber + "." + getFileName[getFileName.length - 1];
		String path = "/Users/sophia/Desktop/workspace/java_eclipsse/SMASH_Spring/src/main/webapp/resources/img/programimg/";

		try (FileOutputStream fos = new FileOutputStream(path + reName); InputStream is = file.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}

		return "redirect:Program";
	}

	@RequestMapping("ProgramEdit")
	public String mediProgrameEdit(HttpServletRequest request, Model model) {

		String programNumber = request.getParameter("programNumber");

		IProgramDao dao = sqlSession.getMapper(IProgramDao.class);
		model.addAttribute("list", dao.getDB(programNumber));

		return "medic/medic_program_edit";
	}

	@RequestMapping(value = "ProgramEditOK", method = RequestMethod.POST)
	public String medicProgramEditOk(HttpServletRequest request, Model model) {

		String programNumber = request.getParameter("programNumber");
		String programName = request.getParameter("programName");
		String programContent = request.getParameter("programContent");
		String programDisease = request.getParameter("programDisease");

		IProgramDao dao = sqlSession.getMapper(IProgramDao.class);
		dao.programEdit(programName, programContent, programDisease, programNumber);

		return "redirect:Program";
	}

	@RequestMapping("ProgramDelete")
	public String medicExerciseDelete(HttpServletRequest request, Model model) {

		String programNumber = request.getParameter("programNumber");

		IProgramDao dao = sqlSession.getMapper(IProgramDao.class);
		dao.programDelete(programNumber);

		return "redirect:Program";
	}

	@RequestMapping("changepwd")
	public String medicChangepwd(HttpServletRequest request, Model model) {
		model.addAttribute("status", 0);

		return "medic/medic_changepwd";
	}

	@RequestMapping("changepwdCheck")
	public String medicChangepwdCheck(HttpSession session, HttpServletRequest request, Model model) {
		int status = 0;
		String current_id = (String) session.getAttribute("id");
		String current_pwd = request.getParameter("currentpwd");
		String new_pwd = request.getParameter("newpwd");
		String new_pwd_check = request.getParameter("newpwd_check");

		model.addAttribute("current_id", current_id);

		IMedicDao dao = sqlSession.getMapper(IMedicDao.class);
		ArrayList<MedicDto> dtos = dao.getPwd(current_id);
		String db_pwd = dtos.get(0).getPassword();

		if (!current_pwd.equals(db_pwd)) { // 현재 비밀번호랑 다르면
			status = 1;
		} else if (!new_pwd.equals(new_pwd_check)) { // 비밀번호 확인이 다르면
			status = 2;
		} else {
			status = 0;
			dao.changePwd(new_pwd, current_id);
		}
		model.addAttribute("status", status);

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
