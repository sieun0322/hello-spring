package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

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
    public void init() throws Exception {
        System.out.println("init()");
        connect();
        call("초기화 연결 메세지");
    }

    public void close() throws Exception {
        System.out.println("close");
        disconnect();
    }
}
