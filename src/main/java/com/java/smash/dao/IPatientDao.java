package com.java.smash.dao;

import java.util.ArrayList;

import com.java.smash.dto.PatientDto;

public interface IPatientDao {
	public ArrayList<PatientDto> patientList(String employeeNumber);
	
//	public ArrayList<PatientDto> patientListbyId(String employeeId);

	public ArrayList<PatientDto> patientEditList(String patientNumber);

	public void patientInsert(String patientNumber, String patientName, String patientDisease, String patientStatus,
			String patientProgram, String employeeNumber);

	public void patientUpdate(String patientName, String patientDisease, String patientStatus, String patientProgram, 
			String patientNumber);

	public void patientDelete(String patientNumber);
	
	public ArrayList<PatientDto> patientSelectList(String patientNumber);
}