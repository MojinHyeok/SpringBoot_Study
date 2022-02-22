package hello.core.singleton;

import org.springframework.boot.SpringApplication;

public class SingletonService {
	
	//1. static 영역에 객체를 딱 1개만 생성해둔다
	private static final SingletonService instance =new SingletonService();
	
	//2. public 으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
	public static SingletonService getInstance() {
		return instance;
	}
	
	//프라이빗 생성자를 사용하여 생성을 막아주는 역할을 합니다.
	private SingletonService() {
		
	}
	public void logic() {
		System.out.println("싱글톤 객체 로직 호출");
	}
}
