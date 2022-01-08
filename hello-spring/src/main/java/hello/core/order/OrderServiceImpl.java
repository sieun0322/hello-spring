package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

	// 필드 주입 +@Autowired : 외부에서 변경 불가. 테스트시 사용.
	private final MemberRepository memberRepository;
	//DIP
	private final DiscountPolicy discountPolicy;

	/**
	 * setter 의존관계 주입
	 * @param memberRepository

	@Autowired(required = false)
	public void setMemberRepository(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	@Autowired
	public void setDiscountPolicy(DiscountPolicy discountPolicy){
		this.discountPolicy = discountPolicy;
	}
	*/
	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	/**
	 * 일반 메서드 주입, 사용X.
	 * @param memberRepository
	 * @param discountPolicy

	@Autowired

	public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}
	 */
	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		// TODO Auto-generated method stub
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member,itemPrice);
		
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	//TEST
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
