package hello.aop;

import hello.aop.order.aop.AspectV1;
import hello.aop.order.aop.AspectV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@Import(AspectV2.class)
public class AopApplication {
    public static void main(String[] args){
        SpringApplication.run(AopApplication.class,args);
    }
}
