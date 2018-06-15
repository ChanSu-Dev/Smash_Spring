package com.java.smash.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.java.smash.dao.IExerciseDao;
import com.java.smash.dao.IMedicDao;
import com.java.smash.dao.IPatientDao;
import com.java.smash.dao.IProgramDao;
import com.java.smash.dto.ExerciseDto;
import com.java.smash.dto.MedicDto;
import com.java.smash.dto.PatientDto;
import com.java.smash.dto.PatientExerciseDto;
import com.java.smash.util.Constant;

@Controller
@RequestMapping("medic")
public class MedicController {
	@Bean
	public MultipartResolver multipartResolver() {
		org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
		return multipartResolver;
	}

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private HttpSession session;

	String PdeviceNo = null;

	@RequestMapping("Main")
	public String medicMain(HttpSession session, HttpServletRequest request, Model model) {
		// FlashMap으로 넘어온 정보
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);

		if (map != null) {
			String id = (String) map.get("id");
			String pwd = (String) map.get("pwd");

			IMedicDao dao = sqlSession.getMapper(IMedicDao.class);
			String employeeNumber = dao.getEmployeeNum(id);

			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
			session.setAttribute("eNum", employeeNumber);
		}
		return "medic/medic_main";
	}

	@RequestMapping("Patient")
	public String medicPatient(HttpSession session, HttpServletRequest request, Model model) {
		String eNum = (String) session.getAttribute("eNum");
		System.out.println(eNum);

		IPatientDao dao = sqlSession.getMapper(IPatientDao.class);
		model.addAttribute("list", dao.patientList(eNum));

		return "medic/medic_patient";
	}

	@RequestMapping("PatientAdd")
	public String medicPatientAdd(HttpServletRequest requset, Model model) {

		IProgramDao dao = sqlSession.getMapper(IProgramDao.class);
		model.addAttribute("list", dao.programList());

		return "medic/medic_patient_add";
	}

	@RequestMapping(value = "PatientAddOK", method = RequestMethod.POST)
	public String medicPatientAddOk(HttpSession session, HttpServletRequest request, Model model) {

		String eNum = (String) session.getAttribute("eNum");

		String patientNumber = request.getParameter("patientNumber");
		String patientName = request.getParameter("patientName");
		String patientDisease = request.getParameter("patientDisease");
		String patientStatus = request.getParameter("patientStatus");
		String patientProgram_1 = request.getParameter("program_1");
		String patientProgram_2 = request.getParameter("program_2");
		String patientProgram_3 = request.getParameter("program_3");
		String patientProgram_4 = request.getParameter("program_4");
		String patientProgram_5 = request.getParameter("program_5");
		String patientProgram = patientProgram_1 + "," + patientProgram_2 + "," + patientProgram_3 + ","
				+ patientProgram_4 + "," + patientProgram_5;

		IPatientDao dao = sqlSession.getMapper(IPatientDao.class);
		dao.patientInsert(patientNumber, patientName, patientDisease, patientStatus, patientProgram, eNum);

		return "redirect:Patient";
	}

	@RequestMapping("PatientEdit")
	public String medicPatientEdit(HttpServletRequest request, Model model) {

		String patientNumber = request.getParameter("patientNumber");

		IProgramDao Pdao = sqlSession.getMapper(IProgramDao.class);
		model.addAttribute("Plist", Pdao.programList());

		IPatientDao dao = sqlSession.getMapper(IPatientDao.class);
		ArrayList<PatientDto> dtos =  dao.patientEditList(patientNumber);
		
		String patientProgram = dtos.get(0).getPatientProgram();
		ArrayList<PatientExerciseDto> PEdto = new ArrayList<>();
		
		PatientExerciseDto strtmp = new PatientExerciseDto();
		strtmp.setProgram_1(patientProgram.split(",")[0]);
		strtmp.setProgram_2(patientProgram.split(",")[1]);
		strtmp.setProgram_3(patientProgram.split(",")[2]);
		strtmp.setProgram_4(patientProgram.split(",")[3]);
		strtmp.setProgram_5(patientProgram.split(",")[4]);
		
		PEdto.add(strtmp);
		
		model.addAttribute("list", dtos);
		model.addAttribute("PEdto", PEdto);

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
		String patientProgram = patientProgram_1 + "," + patientProgram_2 + "," + patientProgram_3 + ","
				+ patientProgram_4 + "," + patientProgram_5;
		
		IPatientDao dao = sqlSession.getMapper(IPatientDao.class);
		dao.patientUpdate(patientName, patientDisease, patientStatus, patientProgram, patientNumber);
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
		try { // 연결 되어있는 장치 넘기기
			String dno = deviceNum.getPatientDevice(pNum);
			model.addAttribute("deviceNum", dno);
		} catch (Exception e) {
			model.addAttribute("deviceNum", "NULL");
		}

		IProgramDao programList = sqlSession.getMapper(IProgramDao.class);
		for (int i = 1; i < 6; i++) { // 프로그램 이름 넘기기
			model.addAttribute("program_" + i, programList.getProgramName(i, pNum));
		}

		// Total 운동 진행상황
		Calendar cal = Calendar.getInstance();

		IExerciseDao exerciseList = sqlSession.getMapper(IExerciseDao.class);
		ArrayList<ExerciseDto> exerDto = exerciseList.getTotalExer(pNum);
		Date d = cal.getTime(); // 제일 오래된 운동 데이터 날짜
		for (int i = 0; i < exerDto.size(); i++) {
			if (d.compareTo(exerDto.get(i).getExerciseTime()) > 0) {
				d = exerDto.get(i).getExerciseTime();
			}
		}

		ArrayList<PatientDto> patientDto = patientDao.patientSelectList(pNum);
		int cnt = 0;
		// if (!patientDto.get(0).getProgram_1().equals("0"))
		// cnt++;
		// if (!patientDto.get(0).getProgram_2().equals("0"))
		// cnt++;
		// if (!patientDto.get(0).getProgram_3().equals("0"))
		// cnt++;
		// if (!patientDto.get(0).getProgram_4().equals("0"))
		// cnt++;
		// if (!patientDto.get(0).getProgram_5().equals("0"))
		cnt++;
		model.addAttribute("cnt", cnt);

		long d1 = cal.getTime().getTime();
		long d2 = d.getTime();
		int totalExer = (int) ((d1 - d2 + 1) / (1000 * 60 * 60 * 24)) * cnt; // 운동 시작 후 요일 수

		model.addAttribute("totalExer", String.valueOf(totalExer));
		model.addAttribute("doExer", String.valueOf(exerDto.size()));

		// 지난 주간의 운동
		HashMap<String, Integer> map = new HashMap();
		ArrayList<String> agoDays = new ArrayList<String>();
		for (int i = 0; i < 7; i++) {
			agoDays.add(getPastDate(i)); // 오늘 날짜 - 7일까지 가져옴
			map.put(agoDays.get(i), 0); // HashMap 저장
		}

		for (int i = 0; i < exerDto.size(); i++) {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = transFormat.format(exerDto.get(i).getExerciseTime());
			if (map.containsKey(date)) {
				map.put(date, map.get(date) + 1);
			}
		}

		for (String key : map.keySet()) {
			System.out.println(String.format("키 : %s, 값 : %s", key, map.get(key)));
		}
		model.addAttribute("map", map);

		return "medic/medic_patient_info";
	}

	private String getPastDate(int i) {

		Calendar cal = Calendar.getInstance();
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH) + 1;
		int day = cal.get(cal.DATE);

		cal.set(year, month - 1, day);
		cal.add(Calendar.DATE, -i);

		Date agoDate = cal.getTime();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		System.out.println(formatter.format(agoDate));
		return formatter.format(agoDate);
	}

	@RequestMapping(value = "Device")
	public String medicDevice(HttpServletRequest request, Model model) {

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		model.addAttribute("list", dao.medicDeviceList());

		return "medic/medic_device";
	}

	@RequestMapping("DeviceAdd")
	public String medicDeviceAdd(HttpSession session, HttpServletRequest request, Model model) {

		// String employeeId = (String) session.getAttribute("id");
		// IPatientDao dao = sqlSession.getMapper(IPatientDao.class);
		// model.addAttribute("list", dao.patientListbyId(employeeId));

		return "medic/medic_device_add";
	}

	@RequestMapping(value = "DeviceAddOk", method = RequestMethod.POST)
	public String medicDeviceAddOk(HttpServletRequest request, Model model) {

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
	public String medicDeviceEdit(HttpServletRequest request, Model model) {
		PdeviceNo = request.getParameter("deviceNumber");

		String deviceNum = request.getParameter("deviceNumber");

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		model.addAttribute("list", dao.deviceEdit(deviceNum));

		return "medic/medic_device_edit";
	}

	@RequestMapping("DeviceEditOk")
	public String medicDeviceEditOk(HttpServletRequest request, Model model) {

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
	public String medicDeviceDelete(HttpServletRequest request, Model model) {
		String deviceNum = request.getParameter("deviceNumber");

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		dao.deviceDelete(deviceNum);

		return "redirect:Device";
	}

	@RequestMapping(value = "Connection", method = RequestMethod.GET)
	public String medicConnection(HttpServletRequest request, Model model) {

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		model.addAttribute("list", dao.medicDeviceList());

		return "medic/medic_connection";
	}

	@RequestMapping(value = "ConnectionStart", method = RequestMethod.POST)
	public String medicConnectionStart(HttpSession session, HttpServletRequest request, Model model) {

		String medicNumber = (String) session.getAttribute("id");
		String patientNumber = request.getParameter("patientNumber");
		String deviceNumber = request.getParameter("deviceNumber");

		IDeviceDao dao = sqlSession.getMapper(IDeviceDao.class);
		dao.medicConnectionStartDao(patientNumber, deviceNumber);

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
		ArrayList<MedicDto> dtos = dao.getMedic(current_id);
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
