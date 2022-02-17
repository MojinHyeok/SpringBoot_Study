package com.ssafy.hello.di2;

public class HelloMain {

	public static void main(String[] args) {
		HelloMessage helloMessage = new HelloMessageKor();
//		HelloMessage helloMessage = new HelloMessageEng();
		
		String greeting = helloMessage.hello("안효인");
//		String greeting = helloMessage.hello("Mr. An");
		
		System.out.println(greeting);
	}
	
}
