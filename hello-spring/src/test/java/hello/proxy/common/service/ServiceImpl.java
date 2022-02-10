package hello.proxy.common.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceImpl implements ServiceInterface{
    public void call(){
        log.info("ConcreteService 호출");
    }
}
