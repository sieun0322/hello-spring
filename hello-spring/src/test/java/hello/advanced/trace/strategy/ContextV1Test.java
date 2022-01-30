package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogin1;
import hello.advanced.trace.template.code.SubClassLogin2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
    @Test
    void templateMethodV0(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();
        log.info("비즈니스 로직1 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = System.currentTimeMillis();
        log.info("resultTime={}",resultTime);
    }
    private void logic2(){
        long startTime = System.currentTimeMillis();
        log.info("비즈니스 로직2 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = System.currentTimeMillis();
        log.info("resultTime={}",resultTime);
    }

    @Test
    void templateMethodV1(){
        AbstractTemplate template1 = new SubClassLogin1();
        template1.excute();
        AbstractTemplate template2 = new SubClassLogin2();
        template2.excute();
    }
    @Test
    void templateMethodV2(){
        AbstractTemplate template1 = new AbstractTemplate(){
            @Override
            protected void call(){
                log.info("비즈니스 로직1 실행");
            }
        };
        log.info("클래스 이름1={}",template1.getClass());
        template1.excute();
        AbstractTemplate template2 = new AbstractTemplate(){
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        log.info("클래스 이름2={}",template2.getClass());
        template2.excute();
    }
    @Test
    void strategyV1(){
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();

    }
}
