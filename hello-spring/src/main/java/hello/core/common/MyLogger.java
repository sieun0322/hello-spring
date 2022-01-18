package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * request : 웹에 필요한 정보는 웹에서만 사용가능.
 */
@Component
@Scope(value = "request")
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
