package com.java.smash.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.java.smash.dto.DeviceDto;
import com.java.smash.util.Constant;

public class DeviceDao {
	DataSource dataSource;

	JdbcTemplate template;

	@Autowired
	public DeviceDao() {		
		template = Constant.template;
	}
	
	public ArrayList<DeviceDto> list(){
		String query = "select * from device";
		return (ArrayList<DeviceDto>) template.query(query, new BeanPropertyRowMapper<DeviceDto>(DeviceDto.class));
	}
}
