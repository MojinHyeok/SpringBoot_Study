package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
@Component
public class OrderServiceImpl  implements OrderService{

	//변경전 
//	private final MemberRepository memberRepository = new MemoryMemberRepository();
//	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//	private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
	//할인 정책을 변경하려면 위와 같이 새롭게 선언을 해주어야한다
//	private DiscountPolicy discountPolicy;
	//이렇게 코드를 변경하면 됩니다.
	//인터페이스에만 의존하는 경우가 됩니다.
	//변경후
	private DiscountPolicy discountPolicy;
	private MemberRepository memberRepository;
	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository,DiscountPolicy discountPolicy) {
		this.discountPolicy = discountPolicy;
		this.memberRepository = memberRepository;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member=memberRepository.findeById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
	
		return new Order(memberId,itemName,itemPrice,discountPrice);
	}
	
	//테스트용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
	
}
