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
    @Test
    void typeExactMatch(){
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    /**
     * 부모 interface 타입매치
     */
    @Test
    void typeMatchSuperType(){
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");//반환타입, 메서드 이름, 파라미터
        Method internalMethod = MemberServiceImpl.class.getMethod("internal",String.class);
        Assertions.assertThat(pointcut.matches(internalMethod,MemberServiceImpl.class)).isTrue();
    }
    /**
     * 부모 interface에 선언된 메서드만 매칭 가능
     */
    @Test
    void typeMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");//반환타입, 메서드 이름, 파라미터
        Method internalMethod = MemberServiceImpl.class.getMethod("internal",String.class);
        Assertions.assertThat(pointcut.matches(internalMethod,MemberServiceImpl.class)).isFalse();
    }
    //파라미터 매칭
    @Test
    void argsMatch() {
        pointcut.setExpression("execution(* *(String))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void argsMatchNoArgs() {
        pointcut.setExpression("execution(* *())");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }
    //정확히 하나의 파라미터 허용, 모든 타입 허용
    @Test
    void argsMatchStar() {
        pointcut.setExpression("execution(* *(*))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    //(),(Xxx),(Xxx,Xxx)
    @Test
    void argsMatchAll() {
        pointcut.setExpression("execution(* *(..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    //(String),(String,Xxx),(String,Xxx,Xxx)
    @Test
    void argsMatchComplex() {
        pointcut.setExpression("execution(* *(String,..))");//반환타입, 메서드 이름, 파라미터
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }




}
