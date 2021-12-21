package hello.core.member;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

	MemberService memberService = new MemberServiceImpl();
	
	@Test
	void 회원가입() {
		// given
		Member member = new Member(1L,"memberA",Grade.VIP);
		
		// when
		memberService.join(member);
		Member findMember = memberService.findMember(1L);

		// then
		assertThat(member).isEqualTo(findMember);

	}

}
