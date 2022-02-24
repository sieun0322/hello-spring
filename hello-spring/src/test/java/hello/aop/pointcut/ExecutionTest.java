package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import java.lang.reflect.Method;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello",String.class);
    }
    @Test
    void printMethod(){
        log.info("helloMethod={}",helloMethod);
    }
    @Test
    void exactMatch(){
        pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void allMatch(){
        pointcut.setExpression("execution(* *(..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void nameMatch(){
        pointcut.setExpression("execution(* hello(..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void nameMatchStar1(){
        pointcut.setExpression("execution(* hel*(..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void nameMatch2(){
        pointcut.setExpression("execution(* *el*(..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void nameMatchFalse(){
        pointcut.setExpression("execution(* nono(..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }
    @Test
    void packageExactMatch1(){
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.hello(..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void packageExactMatch2(){
        pointcut.setExpression("execution(* hello.aop.member.*.*(..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void packageExactFalse(){
        pointcut.setExpression("execution(* hello.aop.*.*(..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }
    @Test
    void packageMatchSubPackage1(){
        pointcut.setExpression("execution(* hello.aop.member..*.*(..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void packageMatchSubPackage2(){
        pointcut.setExpression("execution(* hello.aop..*.*(..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }


}
