package com.jackpan.springbootprinciple.chapter7.advisor4;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * GreetingAdvisor操作类
 *
 * @author JackPan
 * @date 2022/05/15 16:56
 **/
public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "greetTo".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return Waiter.class::isAssignableFrom;
    }


}
