package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

	@Test
	void configurationTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberServiceImpl memberService=ac.getBean("memberService",MemberServiceImpl.class);
		OrderServiceImpl orderService=ac.getBean("orderService",OrderServiceImpl.class);
		MemberRepository memberRepository= ac.getBean("memberRepository",MemberRepository.class);
		
		MemberRepository memberRepository1=memberService.getMemberRepository();
		MemberRepository memberRepository2=orderService.getMemberRepository();
		
		System.out.println("memberService-> memberRepository = "+memberRepository1);
		System.out.println("orderService-> memberRepository = "+memberRepository2);
		System.out.println("memberRepository = "+memberRepository);
		//결과값
//		memberService-> memberRepository = hello.core.member.MemoryMemberRepository@c4ed84
//		orderService-> memberRepository = hello.core.member.MemoryMemberRepository@c4ed84
//		memberRepository = hello.core.member.MemoryMemberRepository@c4ed84
		assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
		
	}
	
	@Test
	void configurationDeep() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		AppConfig bean = ac.getBean(AppConfig.class);
		System.out.println("Bean = "+bean.getClass());
		//결과값
		//Bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$6323e4d0
	}
}
