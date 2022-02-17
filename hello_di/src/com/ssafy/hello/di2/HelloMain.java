package com.ssafy.hello.di2;

public class HelloMain {

	public static void main(String[] args) {
		HelloMessage helloMessage = new HelloMessageKor();
//		HelloMessage helloMessage = new HelloMessageEng();
		
		String greeting = helloMessage.hello("모진혁");
//		String greeting = helloMessage.hello("Mr. Mo");
		
		System.out.println(greeting);
	}
	
}
