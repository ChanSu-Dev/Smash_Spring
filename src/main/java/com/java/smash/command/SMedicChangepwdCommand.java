package com.java.smash.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.java.smash.dao.MedicDao;
import com.java.smash.dto.MedicDto;

public class SMedicChangepwdCommand implements SCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int status = 0;
		String current_id = (String) map.get("current_id");
		String current_pwd = request.getParameter("currentpwd");
		String new_pwd = request.getParameter("newpwd");
		String new_pwd_check = request.getParameter("newpwd_check");
		
		MedicDao dao = new MedicDao();
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
	}
}
