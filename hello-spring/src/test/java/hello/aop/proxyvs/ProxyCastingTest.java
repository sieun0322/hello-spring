package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {
    @Test
    void jdkProxy(){
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false);//jdk 동적프록시

        //인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService)proxyFactory.getProxy();
        //구현클래스로 캐스팅 시도 실패. ClassCastException
        Assertions.assertThrows(ClassCastException.class,()->{
            MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        });
    }
    @Test
    void cglibProxy(){
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);//CGLIB 프록시

        //인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService)proxyFactory.getProxy();
        log.info("",memberServiceProxy.getClass());
        //구현클래스로 캐스팅 성공. ClassCastException
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;

    }
}
