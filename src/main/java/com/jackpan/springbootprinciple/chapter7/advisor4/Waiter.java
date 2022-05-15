package com.jackpan.springbootprinciple.chapter7.advisor4;

/**
 * Waiter操作类
 *
 * @author JackPan
 * @date 2022/05/15 16:52
 **/
public class Waiter {

    public void greetTo(String name) {
        System.out.println("waiter greet to " + name + "...");
    }

    public void serveTo(String name) {
        System.out.println("waiter serving " + name + "...");
    }
}
