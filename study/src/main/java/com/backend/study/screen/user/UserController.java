package com.backend.study.screen.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.study.util.JwtUtil;

@RestController
public class UserController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private UserService userService;
	
	@GetMapping("/")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/user/login")
	public ResponseEntity<Map<String, Object>> login(@RequestParam String id,
			@RequestParam String password) {
			HashMap<String, Object> resultMap = new HashMap<>();
			
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
	@PostMapping("/user/regist")
	public ResponseEntity<Map<String, Object>> regist(@RequestBody UserDto user){
		HashMap<String , Object> resultMap= new HashMap<>();
		UserDto result= userService.RegistUser(user);
		resultMap.put("data", result);
		resultMap.put("성공 여부", "succes");
		return new ResponseEntity<>(resultMap,HttpStatus.OK);
		
	}
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest ) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		return jwtUtil.generateToken(authRequest.getUserName());
	}
	
}
