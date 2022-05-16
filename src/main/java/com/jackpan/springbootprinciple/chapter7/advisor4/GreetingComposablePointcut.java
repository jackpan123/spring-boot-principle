package com.jackpan.springbootprinciple.chapter7.advisor4;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * GreetingComposablePointcut操作类
 *
 * @author JackPan
 * @date 2022/05/15 21:38
 **/
public class GreetingComposablePointcut {

    public Pointcut getIntersectionPointcut() {
        ComposablePointcut pointcut = new ComposablePointcut();
        Pointcut pt1 = new ControlFlowPointcut(WaiterDelegate.class, "serviceClient");
        NameMatchMethodPointcut pt2 = new NameMatchMethodPointcut();
        pt2.addMethodName("greetTo");

        return pointcut.intersection(pt1).intersection((Pointcut) pt2);
    }
}
