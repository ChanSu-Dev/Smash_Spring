package com.java.smash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.java.smash.dto.DeviceDto;
import com.java.smash.util.Constant;

public class DeviceDao {
	DataSource dataSource;

	JdbcTemplate template;

	@Autowired
	public DeviceDao() {
		template = Constant.template;
	}

	public ArrayList<DeviceDto> list() {
		String query = "select * from device";
		return (ArrayList<DeviceDto>) template.query(query, new BeanPropertyRowMapper<DeviceDto>(DeviceDto.class));
	}

	public void insert(final String deviceNo, final String version, final String sort, final String ipv4,
			final String ipv6, final String place) {
		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				// TODO Auto-generated method stub
				String query = "insert into device values (?, ?, ?, 0, ?, ?, ?)";
				PreparedStatement pstmt = arg0.prepareStatement(query);
				pstmt.setString(1, deviceNo);
				pstmt.setString(2, version);
				pstmt.setString(3, sort);
				pstmt.setString(4, ipv4);
				pstmt.setString(5, ipv6);
				pstmt.setString(6, place);
				return pstmt;
			}
		});
	}
	
	public ArrayList<DeviceDto> edit(final String deviceNo) {
		String query = "select * from device where deviceNumber = '" + deviceNo + "'";
		return (ArrayList<DeviceDto>) template.query(query, new BeanPropertyRowMapper<DeviceDto>(DeviceDto.class));
		
	}

	public void delete(final String deviceNo) {
		// TODO Auto-generated method stub
		String query = "delete from device where deviceNumber = ?";
		template.update(query, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				// TODO Auto-generated method stub
				arg0.setString(1, deviceNo);
			}
		});

	}

}
