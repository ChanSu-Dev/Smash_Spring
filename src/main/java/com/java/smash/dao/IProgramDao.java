package com.java.smash.dao;

import java.util.ArrayList;

import com.java.smash.dto.ProgramDto;

public interface IProgramDao {
	public ArrayList<ProgramDto> programList();

	public String getProgramName(String programNumber);

	public void programInsert(String programNumber, String programType, String programName, String programContent, String programDisease, String startPoster, String arrivePoster, int dist);

	public void programDelete(String programNumber);

	public void programEdit(String programType, String programName, String programContent, String programDisease, String startPoster, String arrivePoster, int dist, String programNumber);

	public ArrayList<ProgramDto> getDB(String programNumber);
}
