package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import hello.aop.member.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.lang.reflect.Method;

@Slf4j
@Import({AtTargetAtWithinTest.Config.class})
@SpringBootTest
public class AtTargetAtWithinTest {
    @Autowired
    Child child;

    @Test
    void success(){
        log.info("child Proxy={}", child.getClass());
        child.childMethod();
        child.parentMethod();

    }
    static class Config {
        @Bean
        public Parent parent() {
            return new Parent();
        }

        @Bean
        public Child child() {
            return new Child();
        }

        @Bean
        public AtTargetAtWithinAspect atTargetAtWithinAspect() {
            return new AtTargetAtWithinAspect();
        }
    }
        static class Parent{
            public void parentMethod(){}
        }
        @ClassAop
        static class Child extends Parent{
            public void childMethod(){}
        }
        @Slf4j
        @Aspect
        static class AtTargetAtWithinAspect{
            @Around("execution(* hello.aop..*(..)) && @target(hello.aop.member.annotation.ClassAop)")
            public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable{
                log.info("[@target] {}",joinPoint.getSignature());
                return joinPoint.proceed();
            }
            @Around("execution(* hello.aop..*(..)) && @within(hello.aop.member.annotation.ClassAop)")
            public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable{
                log.info("[@within] {}",joinPoint.getSignature());
                return joinPoint.proceed();
            }
        }


}
