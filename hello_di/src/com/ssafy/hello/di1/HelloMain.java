package com.ssafy.hello.di1;

public class HelloMain {

	public static void main(String[] args) {
		HelloMessageKor helloMessageKor = new HelloMessageKor();
//		HelloMessageEng helloMessageEng = new HelloMessageEng();
		
		String greeting = helloMessageKor.helloKor("모진혁");
//		String greeting = helloMessageEng.helloEng("Mr. 모");
		
		System.out.println(greeting);
	}
	
}
