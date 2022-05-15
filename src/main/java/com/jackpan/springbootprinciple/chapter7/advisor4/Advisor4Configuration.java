package com.jackpan.springbootprinciple.chapter7.advisor4;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Advisor4Configuration操作类
 *
 * @author JackPan
 * @date 2022/05/15 17:06
 **/
@Configuration
public class Advisor4Configuration {

    @Bean
    public Waiter waiterTarget() {
        return new Waiter();
    }

    @Bean
    public Seller sellerTarget() {
        return new Seller();
    }

    @Bean
    public GreetingBeforeAdvice greetingAdvice() {
        return new GreetingBeforeAdvice();
    }

    @Bean
    public GreetingAdvisor greetingAdvisor() {
        GreetingAdvisor greetingAdvisor = new GreetingAdvisor();
        greetingAdvisor.setAdvice(greetingAdvice());
        return greetingAdvisor;
    }

    public ProxyFactoryBean parent() {
        ProxyFactoryBean factory = new ProxyFactoryBean();
        factory.setInterceptorNames("greetingAdvisor");
        factory.setProxyTargetClass(true);
        return factory;
    }

    @Bean
    public ProxyFactoryBean waiter() {
        ProxyFactoryBean parent = parent();
        parent.setTarget(waiterTarget());
        return parent;
    }

    @Bean
    public ProxyFactoryBean seller() {
        ProxyFactoryBean parent = parent();
        parent.setTarget(sellerTarget());
        return parent;
    }
}
