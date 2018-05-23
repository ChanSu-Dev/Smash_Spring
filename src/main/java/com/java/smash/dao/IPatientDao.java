package com.java.smash.dao;

import java.util.ArrayList;

import com.java.smash.dto.PatientDto;

public interface IPatientDao {
	public ArrayList<PatientDto> listDao();

	public ArrayList<PatientDto> editListDao(String patientNumber);

	public void insertDao(String patientNumber, String patientName, String patientDisease, String patientStatus,
			String patientProgram_1, String patientProgram_2, String patientProgram_3);

	public void updateDao(String patientName, String patientDisease, String patientStatus,
			String patientProgram_1, String patientProgram_2, String patientProgram_3, String patientNumber);

	public void deleteDao(String patientNumber);
	
	public void patientInfoDao();
}
