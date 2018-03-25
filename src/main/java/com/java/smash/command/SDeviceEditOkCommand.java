package com.java.smash.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.java.smash.dao.DeviceDao;

public class SDeviceEditOkCommand implements SCommand {

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
		String PdeviceNo = (String) map.get("PdeviceNo");		//where문에 들어갈 과거의 deviceNumber
		
		DeviceDao dao = new DeviceDao();
		dao.editOk(deviceNo, version, sort, ipv4, ipv6, place, PdeviceNo);
	}

}
