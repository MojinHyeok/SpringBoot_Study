package com.backend.study.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backend.study.screen.user.UserDto;
import com.backend.study.screen.user.CustomUserDetailService;
import com.backend.study.util.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//여기서 Header에서의 Authorizaton이라는 값을 추출
		String authorizationHeader = request.getHeader("Authorization");
		
		String token =null;
		String userName=null;
		
		//토큰은 "Bearer "로 시작하므로 "Bearer "로 시작하는 것을 찾음 
		if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			//JWT를 해석해주는 클래스를 통해 userName추출
			userName = jwtUtil.extractUsername(token);
		}
		
		//추출한 userName이 null이 아니라는것은 token의 값이 정상적이라는것을 나타내고, 
		//SecurityContextHolder (Spring Security의 필요한 값을 담는 공간)의 authentication이 비어 있다면 이는 최초 인증이라는 뜻이므로 
		// userName을 통해서 Spring Security Authentication에 필요한 정보를 setting 한다. 


		if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails= service.loadUserByUsername(userName);
			if(jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		// 작업이 끝났다면 다음 필터를 수행하기 위하여 Chain에 태웁니다.
		filterChain.doFilter(request, response);
	}
	//Spring Security Config 에서 나온 JwtFilter. JWt를 사용할 때 가장 핵심적인 기능
	
	

}
