package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * request : 웹에 필요한 정보는 웹에서만 사용가능.
 * proxy : 가짜객체 생성, provider와 같이 진짜 객체 조회를 꼭 필요시점까지 지연처리한다.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;


    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }
    public void log(String message){
        System.out.println("[" + uuid+"]"+"[" + requestURL+"] "+ message);
    }
    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid+"] request scope bean create:"+this);
    }
    @PreDestroy
    public void close(){
        System.out.println("[" + uuid+"] request scope bean close:"+this);

    }
}
