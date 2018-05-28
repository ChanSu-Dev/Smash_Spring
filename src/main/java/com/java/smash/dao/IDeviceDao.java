package com.java.smash.dao;

public interface IDeviceDao {
	// 환자 정보에서 식별기기 아이디값을 가져오는 함수
	public String getPatientDevice(String patientNumber);
}
