package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreateLogic{
    private ConcreateLogic concreateLogic;

    public TimeProxy(ConcreateLogic concreateLogic) {
        this.concreateLogic = concreateLogic;
    }

    @Override
    public String operation(){
        log.info("TimeDecorator 실행");
        long startTimeMs = System.currentTimeMillis();

        String result = concreateLogic.operation();

        long endTimeMs = System.currentTimeMillis();
        long resultTimeMs = endTimeMs-startTimeMs;

        log.info("TimeDecorator 종료 resultTime={}ms",resultTimeMs);

        return result;
    }
}
