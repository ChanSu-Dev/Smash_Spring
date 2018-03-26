package com.java.smash.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.java.smash.dao.DeviceDao;

public class SConnectionQueryCommand implements SCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		
		String status = (String) map.get("status");
		String deviceNumber = (String) map.get("deviceNumber");
		
		DeviceDao dao = new DeviceDao();
		dao.connectionQuery(status, deviceNumber);

	}

}
