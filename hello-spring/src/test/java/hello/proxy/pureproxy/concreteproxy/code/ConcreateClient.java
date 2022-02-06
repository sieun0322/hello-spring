package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreateClient {
    private ConcreateLogic concreateLogic;

    public ConcreateClient(ConcreateLogic concreateLogic) {
        this.concreateLogic = concreateLogic;
    }

    public void execute(){
        concreateLogic.operation();
    }
}
