package com.java.smash.dao;

import java.util.ArrayList;

import com.java.smash.dto.DeviceDto;

public interface IDeviceDao {
	// 환자 정보에서 식별기기 아이디값을 가져오는 함수
	public String getPatientDevice(String patientNumber);
	
	public ArrayList<DeviceDto> deviceList();
	
	public ArrayList<DeviceDto> adminDeviceList();

	public ArrayList<DeviceDto> medicDeviceList();

	public void deviceInsert(String deviceNo, String version, String sort, String ipv4, String ipv6, String place);

	public ArrayList<DeviceDto> deviceEdit(String deviceNo);

	public void deviceEditOk(String deviceNo, String version, String sort, String ipv4, String ipv6, String place,
			String PdeviceNo);

	public void deviceDelete(String deviceNo);

	public void adminConnectionStartDao(String deviceNumber);

	public void medicConnectionStartDao(String patientNumber, String deviceNumber);

	public void connectionStopDao(String deviceNumber);
}
