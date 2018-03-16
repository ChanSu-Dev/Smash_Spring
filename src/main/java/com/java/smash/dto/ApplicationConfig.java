package com.java.smash.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.java.smash.dao.AdminDao;

@Configuration
public class ApplicationConfig {
	
	@Value("${admin.id}")
	private String adminId;
	@Value("${admin.pw}")
	private String adminPw;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer Properties() {
		PropertySourcesPlaceholderConfigurer config = new PropertySourcesPlaceholderConfigurer();
		
		Resource[] location = new Resource[1];
		location[0] = new ClassPathResource("admin.properties");	//설정파일 위치
		config.setLocations(location);
		return config;
	}
	
	@Bean
	public AdminDao adminConfig() {
		AdminDao ad = new AdminDao();
		ad.setAdminId(adminId);
		ad.setAdminPw(adminPw);
		
		return ad;
	}
}
