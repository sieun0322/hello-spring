package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

/**
 * within 하나만 지정 가능
 */
@Slf4j
public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello",String.class);
    }
    void withinExact(){
        pointcut.setExpression("within(hello.aop.member.MemberServiceImpl)");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void withinStar(){
        pointcut.setExpression("within(hello.aop.member.*Service*)");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void withinSubPackage(){
        pointcut.setExpression("within(hello.aop..*)");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    @DisplayName("타켓의 타입에만 직접 적용, 인터페이스 부모 지정 X")
    void withinSuperTypeFalse(){
        pointcut.setExpression("within(hello.aop.member.MemberService)");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }
    @Test
    @DisplayName("타켓의 타입에만 직접 적용, 인터페이스 부모 지정 X")
    void withinSuperTypeTrue(){
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }





}
