package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * JDK 는 대상객체 타입에 의존관계 주입 불가능하다.
 * 인터페이스 기반으로 의존관계를 주입해야 한다.
 *
 * 구체클래스 기반이 필요하다면 CGLIB 사용해야 한다.
 *
 * CGLIB 단점
 * - 대상 클래스에 기본 생성자 필수
 * - 생성자 2번 호출 문제
 *   : 1) 실제 TARGET 객체를 생성할 때
 *     2) 프록시 객체를 생성할 때 부모 클래스의 생성자 호출
 *     생성자 로그 중복, 계산 로직 포함시 문제
 * - final 클래스/메서드 사용 불가
 *
 */
@Slf4j
//@SpringBootTest(properties ={"spring.aop.proxy-target-class=false"}) // JDK
@SpringBootTest(properties ={"spring.aop.proxy-target-class=true"}) // CGLIB
@Import(ProxyDIAspect.class)
public class ProxyDITest {

    @Autowired
    MemberService memberService;

    //JDK 주입 실패.
    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go(){
        log.info("memberService class={}",memberService.getClass());
        log.info("memberServiceImpl class={}",memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }
}
