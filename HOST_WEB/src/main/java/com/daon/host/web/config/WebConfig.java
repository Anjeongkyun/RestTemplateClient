package com.daon.host.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	// (Cross-Origin Resource Sharing,CORS) 해결 설정
	// 참고:
	// https://dev-pengun.tistory.com/entry/Spring-Boot-CORS-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // CORS를 적용할 URL패턴 와일드 카드
				// .allowCredentials(true)
				// .allowedHeaders("*")
				.allowedOrigins("*") // 자원 공유 허락 , * 은 모든 자원 공유
				// .allowedOrigins("http://localhost:8081")
				// .allowedMethods("POST")
				.allowedMethods("*"); // 허용할 메소드 * 도가능, 여러개 지정 가능
	}
}
