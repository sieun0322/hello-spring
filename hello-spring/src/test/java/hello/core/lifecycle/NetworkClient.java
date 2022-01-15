package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 1. implements InitializingBean, DisposableBean
 * 인터페이스 초기화빈, 외부라이브러리 변경 불가능
 * 메서드 이름 변경 불가능
 * 현재 사용 X
 *
 * 2.@Bean(initMethod = "init", destroyMethod = "close")
 * 초기화 종료 함수 지정 가능
 * 외부라이브러리 초기화 종료 메서드 적용 가능
 * -destroyMethod
 * (inferred) 'close','shutdown' 이름의 베서드 자동 호출
 *
 * 3.@PostConstruct @PreDestroy
 * 스프링 권장
 * 자바 표준으로 스프링이 아니어도 사용가능
 * 컴포넌트 스캔과 잘 활용
 * 단점: 외부 라이브러리는 2. 사용해야한다.
 */
public class NetworkClient  {
    private String url;

    public NetworkClient(){
        System.out.println("url = " + url);
    }
    public void setUrl(String url){
        this.url=url;
    }
    public void connect(){
        System.out.println("connect: " + url);
    }
    public void call(String message){
        System.out.println("call: " + url+" message = " + message);
    }
    public void disconnect(){
        System.out.println("close: = " + url);
    }

    /**
     * 의존관계 주입이 끝나면,
     * @throws Exception
     */
    @PostConstruct
    public void init() throws Exception {
        System.out.println("init()");
        connect();
        call("초기화 연결 메세지");
    }
    @PreDestroy
    public void close() throws Exception {
        System.out.println("close");
        disconnect();
    }
}
