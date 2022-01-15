package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 인터페이스 초기화빈, 외부라이브러리 변경 불가능
 * 현재 사용 X
 */
public class NetworkClient implements InitializingBean, DisposableBean {
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
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet()");
        connect();
        call("초기화 연결 메세지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
        disconnect();
    }
}
