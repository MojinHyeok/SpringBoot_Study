package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;

public class MemberServiceTest {
	
	//변경전
//	MemberService memberService = new MemberServiceImpl();
	MemberService memberService; 	
	
	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig =new AppConfig();
		memberService=appConfig.memberService();
	}
	
	@Test
	void join() {
		//given
		Member member=new Member(1L,"memberA",Grade.VIP);
		//when
		memberService.join(member);
		Member findMember = memberService.findMember(1L);
		//tehn
		Assertions.assertThat(member).isEqualTo(findMember);
	}
}
