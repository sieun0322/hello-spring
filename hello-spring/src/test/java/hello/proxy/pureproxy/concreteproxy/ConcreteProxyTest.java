package hello.proxy.pureproxy.concreteproxy;

import hello.proxy.pureproxy.concreteproxy.code.ConcreateClient;
import hello.proxy.pureproxy.concreteproxy.code.ConcreateLogic;
import hello.proxy.pureproxy.concreteproxy.code.TimeProxy;
import hello.proxy.pureproxy.decorator.code.*;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy(){
        ConcreateLogic concreateLogic = new ConcreateLogic();
        ConcreateClient client = new ConcreateClient(concreateLogic);
        client.execute();
    }

    @Test
    void addProxy(){
        ConcreateLogic concreateLogic = new ConcreateLogic();
        TimeProxy timeProxy = new TimeProxy(concreateLogic);
        ConcreateClient client = new ConcreateClient(timeProxy);
        client.execute();
    }
    @Test
    void decorator2(){
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        Component timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }
}
