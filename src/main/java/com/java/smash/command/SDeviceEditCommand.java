package com.java.smash.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.java.smash.dao.DeviceDao;
import com.java.smash.dto.DeviceDto;

public class SDeviceEditCommand implements SCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String deviceNo = request.getParameter("deviceNumber");
		
		DeviceDao dao = new DeviceDao();
		ArrayList<DeviceDto> dtos = dao.edit(deviceNo);
		
		model.addAttribute("list", dtos);
	}

}
