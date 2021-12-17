package hello.hellospring.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import hello.hellospring.domain.Member;

public class JpaMemberRepository implements MemberRepository {

	private final EntityManager em;

	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	
	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		Member member = em.find(Member.class,id);
		return Optional.ofNullable(member);
	}
	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		List<Member> result = em.createQuery("select m from Member m where m.name = :name",Member.class)
						.setParameter("name", name)
						.getResultList();
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m",Member.class).getResultList();
		
	}

}
