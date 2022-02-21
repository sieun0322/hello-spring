package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder(){} //pointcut signature

    //*Service - 트랜젝션 시작
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){} //pointcut signature

    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}
