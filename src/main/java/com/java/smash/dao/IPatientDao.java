package com.java.smash.dao;

import java.util.ArrayList;

import com.java.smash.dto.PatientDto;

public interface IPatientDao {
	public ArrayList<PatientDto> patientList(String employeeNumber);

	public ArrayList<PatientDto> patientEditList(String patientNumber);

	public void patientInsert(String patientNumber, String patientName, String patientDisease, String patientStatus,
			String patientProgram_1, String patientProgram_2, String patientProgram_3, String patientProgram_4,
			String patientProgram_5, String employeeNumber);

	public void patientUpdate(String patientName, String patientDisease, String patientStatus, String patientProgram_1,
			String patientProgram_2, String patientProgram_3, String patientProgram_4, String patientProgram_5,
			String patientNumber);

	public void patientDelete(String patientNumber);
	
	public ArrayList<PatientDto> patientSelectList(String patientNumber);
}
