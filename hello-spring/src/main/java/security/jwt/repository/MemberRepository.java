package security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.jwt.model.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {
    public Member findByName(String name);
}
