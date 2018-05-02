package com.java.smash.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.java.smash.dao.MedicDao;
import com.java.smash.dto.MedicDto;

public class SMedicListCommand implements SCommand {

	@Override
	public void execute(Model model) {
		MedicDao dao = new MedicDao();
		ArrayList<MedicDto> dtos = dao.list();
		model.addAttribute("list", dtos);
	}


}
