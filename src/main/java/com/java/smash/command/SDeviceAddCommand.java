package com.java.smash.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.java.smash.dao.DeviceDao;

public class SDeviceAddCommand implements SCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String deviceNo = request.getParameter("deviceNumber");
		String sort = request.getParameter("sort");
		String version = request.getParameter("version");
		String ipv4 = request.getParameter("ipv4_address");
		String ipv6 = request.getParameter("ipv6_address");
		String place = request.getParameter("place");
		System.out.println(sort);

		DeviceDao dao = new DeviceDao();
		dao.insert(deviceNo, version, sort, ipv4, ipv6, place);
	}

}
