package com.java.smash.dao;

import java.util.ArrayList;

import com.java.smash.dto.ExerciseDto;

public interface IExerciseDao {
	//환자 정보에서 총 운동 진행사항 가져오는 함수
	public ArrayList<ExerciseDto> getTotalExer(String patientNum);
	
	//환자 정보에서 한 주간의 진행사항 가져오는 함수
	public ArrayList<ExerciseDto> getWeekExer(String patientNum);
}
