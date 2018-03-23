package com.java.smash.dto;

public class DeviceDto {
	private String deviceNumber;
	private String version;
	private String sort;
	private int activated;
	private String ipv4_address;
	private String ipv6_address;
	private String place;

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getActivated() {
		return activated;
	}

	public void setActivated(int activated) {
		this.activated = activated;
	}

	public String getIpv4_address() {
		return ipv4_address;
	}

	public void setIpv4_address(String ipv4_address) {
		this.ipv4_address = ipv4_address;
	}

	public String getIpv6_address() {
		return ipv6_address;
	}

	public void setIpv6_address(String ipv6_address) {
		this.ipv6_address = ipv6_address;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
