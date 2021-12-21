package hello.core.member;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface MemberService {
	/**
	 * 회원가입 
	 */
	public void join(Member member);
	
	public Member findMember(Long memberId);
}
