package com.java.smash.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.java.smash.dao.MedicDao;

public class SMediceEditOkCommand implements SCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String employeeNumber = request.getParameter("employeeNumber");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String belong = request.getParameter("belong");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");
		String birth = request.getParameter("birth");
		String PmedicNo = (String) map.get("PmedicNo"); // where문에 들어갈 과거의 employeeNumber

		MedicDao dao = new MedicDao();
		dao.editOk(employeeNumber, name, id, password, belong, contact, address, birth, PmedicNo);
	}

}
