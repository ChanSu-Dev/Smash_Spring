package com.java.smash.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.java.smash.dao.DeviceDao;
import com.java.smash.dto.DeviceDto;

public class SConnectionListCommand implements SCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		DeviceDao dao = new DeviceDao();
		ArrayList<DeviceDto> dtos = dao.list();
		model.addAttribute("list", dtos);
	}

}
