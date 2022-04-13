package com.daon.host.config;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.daon.host.jwt.JwtAccessDeniedHandler;
import com.daon.host.jwt.JwtAuthenticationEntryPoint;
import com.daon.host.jwt.JwtAuthenticationFilter;
import com.daon.host.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity // spring security enable
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final RedisTemplate redisTemplate;

    // css/js/img/ws 관련 인증 무시
    @Override
    public void configure(WebSecurity web) {
    	web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/error/*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            // CSRF 설정 Disable
        http.csrf().disable()

            // exception handling 만든 클래스를 추가
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)

            // 시큐리티는 기본적으로 세션을 사용
            // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
            .and()
            .authorizeRequests()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/ws/**").permitAll()
            .anyRequest().authenticated()   // 나머지 API 는 전부 인증 필요
            
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            
            .and()
            .exceptionHandling()
            .accessDeniedPage("/access-denied")
            
            // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
            // JwtAuthenticationFilter를 UsernamePasswordAuthentictaionFilter 전에 적용시킨다.
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, redisTemplate), 
            		UsernamePasswordAuthenticationFilter.class)
        	.authorizeRequests(); // 권한요청 처리 설정 메서드
            
    }
}
