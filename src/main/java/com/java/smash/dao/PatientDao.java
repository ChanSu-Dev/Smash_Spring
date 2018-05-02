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

import com.java.smash.dto.PatientDto;
import com.java.smash.util.Constant;

public class PatientDao {
	DataSource dataSource;

	JdbcTemplate template;

	@Autowired
	public PatientDao() {
		template = Constant.template;
	}

	public ArrayList<PatientDto> list() {
		String query = "select * from patient";
		return (ArrayList<PatientDto>) template.query(query, new BeanPropertyRowMapper<PatientDto>(PatientDto.class));
	}

	public void insert(final String employeeNumber, final String id, final String password, final String name,
			final String belong, final String contact, final String address, final String birth) {
		// TODO Auto-generated method stub
		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				String query = "insert into medic values(?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = arg0.prepareStatement(query);
				pstmt.setString(1, employeeNumber);
				pstmt.setString(2, id);
				pstmt.setString(3, password);
				pstmt.setString(4, name);
				pstmt.setString(5, belong);
				pstmt.setString(6, contact);
				pstmt.setString(7, address);
				pstmt.setString(8, birth);
				return pstmt;
			}
		});
	}

	public void delete(final String eNo) {
		String query = "delete from medic where employeeNumber = ?";
		template.update(query, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				arg0.setString(1, eNo);
			}
		});
	}

	public ArrayList<PatientDto> edit(final String eNo) {
		String query = "select * from medic where employeeNumber = '" + eNo + "'";
		return (ArrayList<PatientDto>) template.query(query, new BeanPropertyRowMapper<PatientDto>(PatientDto.class));
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

	public ArrayList<PatientDto> getPwd(final String eId) {
		String query = "select * from medic where id = '" + eId + "'";
		return (ArrayList<PatientDto>) template.query(query, new BeanPropertyRowMapper<PatientDto>(PatientDto.class));
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
