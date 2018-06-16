package com.java.smash.dto;

public class ProgramDto {
	private String programNumber;
	private String name;
	private String content;
	private String corrDisease;
	private String StartPoster;
	private String ArrivePoster;
	private int dist;

	public String getProgramNumber() {
		return programNumber;
	}

	public void setProgramNumber(String programNumber) {
		this.programNumber = programNumber;
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
		return StartPoster;
	}

	public void setStartPoster(String startPoster) {
		StartPoster = startPoster;
	}

	public String getArrivePoster() {
		return ArrivePoster;
	}

	public void setArrivePoster(String arrivePoster) {
		ArrivePoster = arrivePoster;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

}
