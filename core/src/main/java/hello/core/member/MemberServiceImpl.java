package hello.core.member;

public class MemberServiceImpl  implements MemberService{

//	private final MemberRepository memberRepository= new MemoryMemberRepository();
	//오버라이드 되어 MemberRespository로 선언되어도 new MemoryMemberRepository의 save와 findByid와 같은 것들로 선언이 됩니다.
	
	//변경후
	private final MemberRepository memberRepository;
	
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository=memberRepository;
	}
	
	@Override
	public void join(Member member) {
		memberRepository.save(member);
		
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findeById(memberId);
	}
	
	//테스트용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
