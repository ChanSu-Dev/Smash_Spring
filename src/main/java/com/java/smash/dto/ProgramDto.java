package com.java.smash.dto;

public class ProgramDto {
	private String programNumber;
	private String programType;
	private String name;
	private String content;
	private String corrDisease;
	private String startPoster;
	private String arrivePoster;
	private int dist;

	public String getProgramNumber() {
		return programNumber;
	}

	public void setProgramNumber(String programNumber) {
		this.programNumber = programNumber;
	}

	public String getProgramType() {
		return programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCorrDisease() {
		return corrDisease;
	}

	public void setCorrDisease(String corrDisease) {
		this.corrDisease = corrDisease;
	}

	public String getStartPoster() {
		return startPoster;
	}

	public void setStartPoster(String startPoster) {
		this.startPoster = startPoster;
	}

	public String getArrivePoster() {
		return arrivePoster;
	}

	public void setArrivePoster(String arrivePoster) {
		this.arrivePoster = arrivePoster;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

}
