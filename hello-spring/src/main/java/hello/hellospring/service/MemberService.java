package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import hello.hellospring.MemberRepository;
import hello.hellospring.MemoryMemberRepository;
import hello.hellospring.domain.Member;

public class MemberService {

	private final MemberRepository memberRepository;
	
	//DI
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/**
	 * 회원가입 
	 */
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m->{
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
	/*
	 * 전체 회원 조회 
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}
