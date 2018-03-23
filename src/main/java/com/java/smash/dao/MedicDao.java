package com.java.smash.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.java.smash.dto.DeviceDto;
import com.java.smash.dto.MedicDto;
import com.java.smash.util.Constant;

public class MedicDao {
	DataSource dataSource;

	JdbcTemplate template;

	@Autowired
	public MedicDao() {		
		template = Constant.template;
	}
	
	public ArrayList<MedicDto> list(){
		String query = "select * from medic";
		return (ArrayList<MedicDto>) template.query(query, new BeanPropertyRowMapper<MedicDto>(MedicDto.class));
	}
}
