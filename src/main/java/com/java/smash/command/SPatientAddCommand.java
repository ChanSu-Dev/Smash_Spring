package com.java.smash.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.java.smash.dao.PatientDao;

public class SPatientAddCommand implements SCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String patientNumber = request.getParameter("patientNumber");
		String patientName = request.getParameter("patientName");
		String patientDisease = request.getParameter("patientDisease");
		String patientStatus = request.getParameter("patientStatus");
		String patientExercise = request.getParameter("patientExercise");
		String patient_deviceNum = request.getParameter("patient_deviceNum");

		PatientDao dao = new PatientDao();
		dao.insert(patientNumber, patientName, patientDisease, patientStatus, patientExercise, patient_deviceNum);
	}

}
