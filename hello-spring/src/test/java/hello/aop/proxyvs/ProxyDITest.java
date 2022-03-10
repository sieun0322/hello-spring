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
