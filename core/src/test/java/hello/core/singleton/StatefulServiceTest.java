package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
	
	@Test
	void statefulServiceSingleton() {
		ApplicationContext ac= new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1= ac.getBean(StatefulService.class);
		StatefulService statefulService2= ac.getBean(StatefulService.class);
//		ThreadA: A사용자 10000원을 주문
		statefulService1.order("userA", 10000);
//		ThreadB: B사용자 20000원을 주문
		statefulService2.order("userA", 20000);
		
		//ThreadA 사용자 a주문 금액 조회
		int price = statefulService1.getPrice();
		System.out.println("price = " +price);
		//가격은 2만원이 나옴 같은 객체를 사용하기 때문에..
		Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}
	
	static class TestConfig{
		@Bean
		public StatefulService statefulService() { 
			return new StatefulService();
		}
	}
}
