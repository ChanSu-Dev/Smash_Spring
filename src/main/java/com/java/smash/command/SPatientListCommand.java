package com.java.smash.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.java.smash.dao.PatientDao;
import com.java.smash.dto.PatientDto;

public class SPatientListCommand implements SCommand {

	@Override
	public void execute(Model model) {
		
		PatientDao dao = new PatientDao();
		ArrayList<PatientDto> dtos = dao.list();
		model.addAttribute("list", dtos);
	}

}
