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

import com.java.smash.dto.ProgramDto;
import com.java.smash.util.Constant;

public class ProgramDao {
	DataSource dataSource;

	JdbcTemplate template;

	@Autowired
	public ProgramDao() {
		template = Constant.template;
	}

	public ArrayList<ProgramDto> list() {
		String query = "select * from program";
		return (ArrayList<ProgramDto>) template.query(query, new BeanPropertyRowMapper<ProgramDto>(ProgramDto.class));
	}

	public void insert(final String programNumber, final String programName, final String programDisease, final String programStatus,
			final String programExercise, final String program_deviceNum) {
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				String query = "insert into program values(?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = arg0.prepareStatement(query);
				pstmt.setString(1, programNumber);
				pstmt.setString(2, programName);
				pstmt.setString(3, programDisease);
				pstmt.setString(4, programStatus);
				pstmt.setString(5, programExercise);
				pstmt.setString(6, program_deviceNum);
				return pstmt;
			}
		});
	}

	public void delete(final String Pno) {
		String query = "delete from program where programNumber = ?";
		template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				arg0.setString(1, Pno);
			}
		});
	}

	public ArrayList<ProgramDto> edit(final String eNo) {
		String query = "select * from medic where employeeNumber = '" + eNo + "'";
		return (ArrayList<ProgramDto>) template.query(query, new BeanPropertyRowMapper<ProgramDto>(ProgramDto.class));
	}

	public void editOk(final String employeeNumber, final String name, final String id, final String password, final String belong, final String contact,
			final String address, final String birth, final String PmedicNo) {
		String query = "update medic set employeeNumber = ?, name = ?, id = ?, password = ?, belong = ?, contact = ?, address = ?, birth = ? where employeeNumber = ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				arg0.setString(1, employeeNumber);
				arg0.setString(2, name);
				arg0.setString(3, id);
				arg0.setString(4, password);
				arg0.setString(5, belong);
				arg0.setString(6, contact);
				arg0.setString(7, address);
				arg0.setString(8, birth);
				arg0.setString(9, PmedicNo);
			}
		});
	}

	public ArrayList<ProgramDto> getPwd(final String eId) {
		String query = "select * from medic where id = '" + eId + "'";
		return (ArrayList<ProgramDto>) template.query(query, new BeanPropertyRowMapper<ProgramDto>(ProgramDto.class));
	}
	
	public void changePwd(final String pwd, final String eId) {
		String query = "update medic set password = ? where id = ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				arg0.setString(1, pwd);
				arg0.setString(2, eId);
			}
		});
	}
}
