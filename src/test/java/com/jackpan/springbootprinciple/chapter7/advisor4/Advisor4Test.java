package com.jackpan.springbootprinciple.chapter7.advisor4;


import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Advisor4Test操作类
 *
 * @author JackPan
 * @date 2022/05/15 17:23
 **/

public class Advisor4Test {


    @Test
    public void advisorTest() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Advisor4Configuration.class);
        Waiter waiter = (Waiter) ctx.getBean("waiter");
        Seller seller = (Seller) ctx.getBean("seller");
        waiter.greetTo("John");
        waiter.serveTo("John");
        seller.greetTo("John");
    }
}
