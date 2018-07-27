package com.java.smash.dao;

import java.util.ArrayList;

import com.java.smash.dto.PatientDto;

public interface IPatientDao {
	// 주치의가 보는 환자 목록
	public ArrayList<PatientDto> patientList();

	// 운동 코디네이터가 보는 환자 목록
	public ArrayList<PatientDto> codiPatientList(String codiNumber);

	public ArrayList<PatientDto> patientEditList(String patientNumber);

	public void patientInsert(String patientNumber, String patientName, String patientDisease, String patientStatus,
			String patientProgram, String employeeNumber, String patientCodi);

	public void patientUpdate(String patientName, String patientDisease, String patientStatus, String patientProgram,
			String patientCodi, String patientNumber);

	public void patientDelete(String patientNumber);

	public ArrayList<PatientDto> patientSelectList(String patientNumber);
}