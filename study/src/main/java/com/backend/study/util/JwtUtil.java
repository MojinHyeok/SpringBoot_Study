package com.backend.study.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class JwtUtil {
//JWT를 생성하고 추출하고 확인하는 모듈
//JwtFilter에 등장했던 JwtUtil이다. 앞서 잠시 설명을 했지만 이는 token을 생성해주는 역할, token으로부터 정보를 추출하는 역할, token의 유효성을 검사하는 역할을 한다. 

	
	
	private String secret = "sercret";
	
	//유저네임 추출하는 부분
	public String extractUsername(String token) {
	
		return extractClaim(token, Claims::getSubject);
	}
	
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
		}
	
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();	
	}
	
	//토큰이 만료됬는지 
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());	
	}
	
	public String generateToken(String username) {
	
		Map<String, Object> claims = new HashMap<>();
		
		return createToken(claims, username);
	
	}
	
	//Spring Security 연습 Jwt를 활용하여 인증 연습
	
	private String createToken(Map<String, Object> claims, String subject) {
	
		//claims라고하는 토큰으로 만들고 싶은 실질적인 값을 넣는 곳 , expiration은 유효시간 설정 부분 ,sign 관련은 token의 signature를 설정 하는 부분
		//이렇게 조합해서 마지막에 compact를 수행하면 string으로 된 jwt생성.
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
		.signWith(SignatureAlgorithm.HS256, secret).compact();
		
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
}
