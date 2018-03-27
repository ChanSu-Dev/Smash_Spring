package com.java.smash.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.java.smash.dao.MedicDao;
import com.java.smash.dto.MedicDto;

public class SMediceEditCommand implements SCommand {

	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String eNo = request.getParameter("employeeNumber");

		MedicDao dao = new MedicDao();
		ArrayList<MedicDto> dtos = dao.edit(eNo);

		model.addAttribute("list", dtos);
	}

}
