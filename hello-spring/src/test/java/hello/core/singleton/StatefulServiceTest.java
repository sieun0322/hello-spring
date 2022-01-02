package hello.core.singleton;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Singleton 방식의 주의점
 */
class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac  = new AnnotationConfigApplicationContext((TestConfig.class));
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        int userAPrice = statefulService1.order("usrA",1000);
        int userBPrice = statefulService2.order("usrB",2000);

        System.out.println("userAPrice = " + userAPrice);
        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(2000);
    }

    static  class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}