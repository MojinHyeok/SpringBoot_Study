package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class OrderServicetest {

	
	//변경전
//	MemberService memberService = new MemberServiceImpl();
//	OrderService orderService =new OrderServiceImpl();
	
	
	//변경후
	MemberService memberService ;
	OrderService orderService;
	
	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig =new AppConfig();
		memberService=appConfig.memberService();
		orderService=appConfig.orderService();
	}
	@Test
	void createOrder() {
		Long memberId=1L;
		Member member=new Member(memberId,"memberA",Grade.VIP); 
		memberService.join(member);
		
		Order order=orderService.createOrder(memberId, "itemA", 10000);
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}
}