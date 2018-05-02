package com.java.smash.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.java.smash.dao.DeviceDao;
import com.java.smash.dto.DeviceDto;

public class SPatientDeviceListCommand implements SCommand {

	@Override
	public void execute(Model model) {
		DeviceDao dao = new DeviceDao();
		ArrayList<DeviceDto> dtos = dao.list();
		model.addAttribute("deviceNum", dtos);
	}
}
