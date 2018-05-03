package com.java.smash.dao;

import java.util.ArrayList;

import com.java.smash.dto.ProgramDto;

public interface IProgramDao {
	public ArrayList<ProgramDto> list();
	public void insert();
	public void delete();
}
