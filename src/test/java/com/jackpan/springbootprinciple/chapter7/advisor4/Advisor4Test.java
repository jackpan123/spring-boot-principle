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

    @Test
    public void regexpAdvisorTest() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Advisor4Configuration.class);
        Waiter waiter1 = (Waiter) ctx.getBean("waiter1");
        Seller seller = (Seller) ctx.getBean("seller");
        waiter1.greetTo("John");
        waiter1.serveTo("John");
        seller.greetTo("John");
    }

    @Test
    public void dynamicAdvisorTest() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Advisor4Configuration.class);
        Waiter waiter2 = (Waiter) ctx.getBean("waiter2");
        Seller seller = (Seller) ctx.getBean("seller");
        waiter2.greetTo("Peter");
        waiter2.serveTo("Peter");
        waiter2.greetTo("John");
        waiter2.serveTo("John");
        seller.greetTo("John");
    }


    @Test
    public void controlFlowAdvisorTest() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Advisor4Configuration.class);
        Waiter waiter3 = (Waiter) ctx.getBean("waiter3");
        WaiterDelegate wd = new WaiterDelegate();
        wd.setWaiter(waiter3);
        waiter3.greetTo("Peter");
        waiter3.serveTo("Peter");

        wd.serviceClient("Peter");

        WaiterDelegate wd2 = new WaiterDelegate();
        wd2.setWaiter(waiter3);

        wd.serviceClient("Peter111");
    }

    @Test
    public void composableAdvisorTest() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Advisor4Configuration.class);
        Waiter waiter4 = (Waiter) ctx.getBean("waiter4");
        WaiterDelegate wd = new WaiterDelegate();
        wd.setWaiter(waiter4);
        waiter4.greetTo("Peter");
        waiter4.serveTo("Peter");

        wd.serviceClient("Peter");

    }
}
