package com.java.smash.dao;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.java.smash.dto.AdminDto;
import com.java.smash.util.Constant;

@Configuration
public class AdminDao {
	
	@Value("${admin.id}")
	private String adminId;
	@Value("${admin.pw}")
	private String adminPw;

	@Bean
	public static PropertySourcesPlaceholderConfigurer Properties() {
		PropertySourcesPlaceholderConfigurer config = new PropertySourcesPlaceholderConfigurer();

		Resource[] location = new Resource[1];
		location[0] = new ClassPathResource("admin.properties"); // 설정파일 위치
		config.setLocations(location);
		return config;
	}

	@Bean
	public AdminDto adminConfig() {
		AdminDto ad = new AdminDto();
		ad.setAdminId(adminId);
		ad.setAdminPw(adminPw);

		return ad;
	}
}
