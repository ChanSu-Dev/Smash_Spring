package com.java.smash.dao;

import java.util.ArrayList;

import com.java.smash.dto.ProgramDto;

public interface IProgramDao {
	public ArrayList<ProgramDto> programList();

	public String getProgramName(int index, String programNumber);

	public void programInsert(String programNumber, String programName, String programContent, String programDisease);

	public void programDelete(String programNumber);

	public void programEdit(String programName, String programContent, String programDisease, String programNumber);

	public ArrayList<ProgramDto> getDB(String programNumber);
}
