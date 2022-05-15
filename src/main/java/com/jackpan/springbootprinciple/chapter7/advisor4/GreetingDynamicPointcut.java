package com.jackpan.springbootprinciple.chapter7.advisor4;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * GreetingDynamicPointcut操作类
 *
 * @author JackPan
 * @date 2022/05/15 20:52
 **/
public class GreetingDynamicPointcut extends DynamicMethodMatcherPointcut {

    private static List<String> specialClientList = new ArrayList<>();

    static {
        specialClientList.add("John");
        specialClientList.add("Tom");
    }


    @Override
    public ClassFilter getClassFilter() {
        return clazz -> {
            System.out.println("调用getClassFilter()对" + clazz.getName() + "做静态检查");
            return Waiter.class.isAssignableFrom(clazz);
        };
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("调用matches(method，targetClass)对" + targetClass.getName()
                + "." + method.getName() + "做静态检查.");
        return "greetTo".equals(method.getName());
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("调用matches(method)对" + targetClass.getName()
                + "." + method.getName() + "做动态检查.");
        String clientName = (String) args[0];
        return specialClientList.contains(clientName);
    }

}
