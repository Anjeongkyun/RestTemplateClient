package com.daon.host.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HostLocalApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(HostLocalApplication.class, args);
	}
	
	/*
	 * //war 배포할떄 추가
	 * 
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder builder) { return
	 * builder.sources(HostLocalApplication.class); }
	 */
}
