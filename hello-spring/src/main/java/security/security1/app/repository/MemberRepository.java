package security.security1.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.security1.app.model.Member;

//JpaRepository를 상속하여 @Repository 없어도 IoC
public interface MemberRepository extends JpaRepository<Member,Integer> {

}
