package com.jackpan.springbootprinciple.chapter7.advisor4;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.ControlFlowPointcut;
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

    // 流程切点
//    @Bean
//    public ControlFlowPointcut controlFlowPointcut() {
//        return new ControlFlowPointcut(WaiterDelegate.class, "adfd");
//    }
//
//    @Bean
//    public DefaultPointcutAdvisor controlFlowAdvisor() {
//        DefaultPointcutAdvisor controlFlowAdvisor = new DefaultPointcutAdvisor();
//        controlFlowAdvisor.setPointcut(controlFlowPointcut());
//        controlFlowAdvisor.setAdvice(greetingAdvice());
//
//        return  controlFlowAdvisor;
//    }
//
//    @Bean
//    public ProxyFactoryBean waiter3() {
//        ProxyFactoryBean factory = new ProxyFactoryBean();
//        factory.setInterceptorNames("controlFlowAdvisor");
//        factory.setProxyTargetClass(true);
//        factory.setTarget(waiterTarget());
//        return factory;
//    }

    // 复合切点
    @Bean
    public GreetingComposablePointcut gcp() {
        return new GreetingComposablePointcut();
    }

    @Bean
    public DefaultPointcutAdvisor composableAdvisor() {
        DefaultPointcutAdvisor composableAdvisor = new DefaultPointcutAdvisor();
        composableAdvisor.setPointcut(gcp().getIntersectionPointcut());
        composableAdvisor.setAdvice(greetingAdvice());

        return composableAdvisor;
    }

    @Bean
    public ProxyFactoryBean waiter4() {
        ProxyFactoryBean factory = new ProxyFactoryBean();
        factory.setInterceptorNames("composableAdvisor");
        factory.setProxyTargetClass(true);
        factory.setTarget(waiterTarget());
        return factory;
    }


}
