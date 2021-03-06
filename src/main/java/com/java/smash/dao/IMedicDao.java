package com.java.smash.dao;

import java.util.ArrayList;

import com.java.smash.dto.MedicDto;

public interface IMedicDao {
	public ArrayList<MedicDto> medicList();
	
	public ArrayList<MedicDto> codiList();

	public void medicInsert(String employeeNumber, String id, String password, String name, String belong,
			String contact, String address, String birth, String regType);

	public void medicDelete(String eNo);

	public ArrayList<MedicDto> medicEdit(String eNo);

	public void medicEditOk(String employeeNumber, String name, String id, String password, String belong,
			String contact, String address, String birth, String regType, String PmedicNo);

	public ArrayList<MedicDto> getMedic(String eId);

	public void changePwd(String pwd, String eId);

	public String getEmployeeNum(String id);

	public String getCodiName(String codiNumber);
}
