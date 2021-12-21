package hello.core.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;



public class MemoryMemberRepository implements MemberRepository {

	private static Map<Long, Member> store = new HashMap<>();
	

	@Override
	public void save(Member member) {
		// TODO Auto-generated method stub
		store.put(member.getId(), member);
	}

	@Override
	public Member findById(Long id) {
		// TODO Auto-generated method stub
		return store.get(id);
	}

	
	public void clearStore() {
		store.clear();
	}

}
