package com.ssafy.hello.di3;

public class HelloMessageFactory {

	public static HelloMessage getHelloMessage(String lang) {
		if("kor".equals(lang)) {
			return new HelloMessageKor();
		} else if("eng".equals(lang)) {
			return new HelloMessageEng();
		} else {
			return null;
		}
	}
	
}
