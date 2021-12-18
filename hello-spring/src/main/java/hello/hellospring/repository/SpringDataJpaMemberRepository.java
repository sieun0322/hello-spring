package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.hellospring.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository{
	
	@Override
	Optional<Member> findByName(String name);
}