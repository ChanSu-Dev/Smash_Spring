package com.java.smash.dao;

import java.util.ArrayList;

import com.java.smash.dto.DeviceDto;

public interface IConnectionDao {
	public ArrayList<DeviceDto> listDao();
	public void connectionStartDao(String patientNumber, String deviceNumber);
	public void connectionStopDao(String deviceNumber);
}
