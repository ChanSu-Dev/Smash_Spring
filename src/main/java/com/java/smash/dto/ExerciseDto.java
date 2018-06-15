package com.java.smash.dto;

import java.util.Date;

public class ExerciseDto {
	private String exerciseNum;
	private String patientNum;
	private String programNum;
	private Date exerciseTime;
	private int DailyStep;

	public String getExerciseNum() {
		return exerciseNum;
	}

	public void setExerciseNum(String exerciseNum) {
		this.exerciseNum = exerciseNum;
	}

	public String getPatientNum() {
		return patientNum;
	}

	public void setPatientNum(String patientNum) {
		this.patientNum = patientNum;
	}

	public String getProgramNum() {
		return programNum;
	}

	public void setProgramNum(String programNum) {
		this.programNum = programNum;
	}

	public Date getExerciseTime() {
		return exerciseTime;
	}

	public void setExerciseTime(Date exerciseTime) {
		this.exerciseTime = exerciseTime;
	}

	public int getDailyStep() {
		return DailyStep;
	}

	public void setDailyStep(int dailyStep) {
		DailyStep = dailyStep;
	}

}
