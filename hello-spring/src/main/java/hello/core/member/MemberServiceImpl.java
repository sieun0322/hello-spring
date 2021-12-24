package hello.core.member;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	public MemberServiceImpl(MemberRepository memberRepository){
		this.memberRepository = memberRepository;
	}

	/**
	 * 회원가입 
	 */
	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}
	
	@Override
	public Member findMember(Long memberId){
		return memberRepository.findById(memberId);
	}
}
