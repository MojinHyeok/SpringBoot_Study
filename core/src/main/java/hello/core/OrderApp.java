package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

	public static void main(String[] args) {
	
		//변경전
//		AppConfig appConfig =new AppConfig();
//		MemberService memberService=appConfig.memberService();
//		OrderService orderService=appConfig.orderService();
		//변경후 
		ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService=applicationContext.getBean("memberService",MemberService.class);
		OrderService orderService=applicationContext.getBean("orderService",OrderService.class);
		Long memberId=1L;
		Member member= new Member(memberId, "memberA",Grade.VIP);
		memberService.join(member);
		Order order = orderService.createOrder(memberId, "itemA", 20000);
		System.out.println("order = "+order);
		System.out.println("order.calculatePrice = " +order.calculatePrice());
		//출력문
		//order = Order [memberId=1, itemName=itemA, itemPrice=10000, discountPrice=1000]
		//order.calculatePrice = 9000

	}
}
