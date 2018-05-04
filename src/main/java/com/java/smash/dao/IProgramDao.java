package com.java.smash.dao;

import java.util.ArrayList;

import com.java.smash.dto.ProgramDto;

public interface IProgramDao {
	public ArrayList<ProgramDto> listDao();
	public void insertDao(String programNumber, String programName, String programContent, String programDisease );
	public void deleteDao(String programNumber);
}
