package com.java.smash.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.java.smash.dao.DeviceDao;

public class SDeviceDeleteCommand implements SCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String deviceNo = request.getParameter("deviceNumber");
		
		DeviceDao dao = new DeviceDao();
		dao.delete(deviceNo);
	}

}
