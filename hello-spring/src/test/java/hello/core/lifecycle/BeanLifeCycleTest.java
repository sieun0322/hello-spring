package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ConfigurableWebApplicationContext;

/**
 * 스프링 빈 이벤트 라이프 사이클
 * 1. 스프링 컨테이너 생성
 * 2. 스프링 빈 생성
 * 3. 의존관계 주입
 * 4. 초기화 콜백
 * 5. 사용
 * 6. 소멸전 콜백
 * 7. 스프링 종료
 */
public class BeanLifeCycleTest {

    @Test
    public void  lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();//ConfigurableApplicationContext 에서 제공
    }
    @Configuration
    static class LifeCycleConfig{
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");

            return networkClient;
        }
    }
}
