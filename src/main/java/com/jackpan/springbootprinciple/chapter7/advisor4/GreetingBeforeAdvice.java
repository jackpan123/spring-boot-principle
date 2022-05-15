package com.jackpan.springbootprinciple.chapter7.advisor4;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * GreetingBeforeAdvice操作类
 *
 * @author JackPan
 * @date 2022/05/15 17:00
 **/
public class GreetingBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + "." + method.getName());
        String clientName = (String) args[0];
        System.out.println("How are you! Mr." + clientName + ".");
    }
}
