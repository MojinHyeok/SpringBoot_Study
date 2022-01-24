package com.backend.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.backend.study.filter.JwtFilter;
import com.backend.study.screen.user.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService userSerivce;
	
	
	@Autowired
	private JwtFilter jwtFilter;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}


	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSerivce);
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().antMatchers("/authenticate","/user/regist")
		.permitAll().anyRequest().authenticated()
		.and().exceptionHandling().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
	}
	//authenticate, /user/regist 에 대한 접속은 모두허용 그외에는 인증과정을 거쳐야 한다.
	// authenticate인 경우에은 jwt 발급해주는 로직이 들어가고 나머지 모든 로직은 jwt를 통해 인증이 된 경우만 사용 가능
	
	//addFilterBefore 는 UsernamePasswordAuthenticationFilter를 통과하기 전에 jwtFilter라는 것을 지나도록 설정 
	//Spring Security에는 많은 filter가 있고 이를 순서대로 통과하며 인증에 대한 처리를 한다.
	// 인증을 하는데 있어서 jwtFilter라는 것을 추가해 request로 부터 날아온 jwt를 처리하여 이에 대한 결과를 사용자 정보에 추가하기 위함.
	//저는 개인적으로 UsernameAndPassword 인증 필터보다 먼저 JWT 인증 필터를 호출하는 것은 필터가 Spring Security Filter Chain 순서를 존중하고 
	//UsernameAndPassword 필터와 직접적으로 관련이 없는지 확인하기 위한 것이라고 생각합니다
	
	
	
}
