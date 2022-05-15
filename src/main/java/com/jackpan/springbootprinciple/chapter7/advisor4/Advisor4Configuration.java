package com.jackpan.springbootprinciple.chapter7.advisor4;

import com.sun.tools.javac.util.List;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
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

    @Bean
    public RegexpMethodPointcutAdvisor regexpAdvisor() {
        RegexpMethodPointcutAdvisor regexpAdvisor = new RegexpMethodPointcutAdvisor();
        regexpAdvisor.setAdvice(greetingAdvice());
        regexpAdvisor.setPatterns(".*greet.*");

        return regexpAdvisor;
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
    public ProxyFactoryBean waiter1() {
        ProxyFactoryBean factory = new ProxyFactoryBean();
        factory.setInterceptorNames("regexpAdvisor");
        factory.setProxyTargetClass(true);
        factory.setTarget(waiterTarget());
        return factory;
    }

    @Bean
    public ProxyFactoryBean seller() {
        ProxyFactoryBean parent = parent();
        parent.setTarget(sellerTarget());
        return parent;
    }

    // 动态切点检查
    @Bean
    public GreetingDynamicPointcut pointcut() {
        return new GreetingDynamicPointcut();
    }
    @Bean
    public DefaultPointcutAdvisor dynamicAdvisor() {
        DefaultPointcutAdvisor dynamicAdvisor = new DefaultPointcutAdvisor();
        dynamicAdvisor.setPointcut(pointcut());
        dynamicAdvisor.setAdvice(greetingAdvice());

        return dynamicAdvisor;
    }

    @Bean
    public ProxyFactoryBean waiter2() {
        ProxyFactoryBean factory = new ProxyFactoryBean();
        factory.setInterceptorNames("dynamicAdvisor");
        factory.setProxyTargetClass(true);
        factory.setTarget(waiterTarget());
        return factory;
    }


}
