package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {
    /**
     * 대안1 자기자신 주입
     */
    private CallServiceV1 callServiceV1;

    //spring.main.allow-circular-references=true 순환참조 허용시.
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1){
        //Bean에 등록되어 있는 프록시가 주입됨
        log.info("callServiceV1 setter = {}",callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }
    public void external(){
        log.info("call external");
        callServiceV1.internal();//외부 메서드 호출
    }
    public void internal(){
        log.info("call internal");
    }
}
