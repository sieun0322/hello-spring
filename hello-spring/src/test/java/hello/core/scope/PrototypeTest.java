package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 스프링 컨테이너 에서 조회시
 * prototype  인스턴스 생성
 */
public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find protytypeBean1 = ");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find protytypeBean2 = ");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close();//스프링 컨테이너에서 관리 X

        //직접 종료메서드 호출
        prototypeBean1.destroy();
        prototypeBean2.destroy();

    }
    @Scope("prototype")
    static  class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("init" );
        }
        @PreDestroy
        public void destroy(){
            System.out.println("destroy" );
        }

    }

}
