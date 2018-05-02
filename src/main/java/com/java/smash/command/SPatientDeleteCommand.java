package com.java.smash.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.java.smash.dao.PatientDao;

public class SPatientDeleteCommand implements SCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String patientNumber = request.getParameter("patientNumber");
		
		PatientDao dao = new PatientDao();
		dao.delete(patientNumber);
	}
}
