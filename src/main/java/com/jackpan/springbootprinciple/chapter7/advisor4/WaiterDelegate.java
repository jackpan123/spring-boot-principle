package com.jackpan.springbootprinciple.chapter7.advisor4;

/**
 * WaiterDelegate操作类
 *
 * @author JackPan
 * @date 2022/05/15 21:24
 **/
public class WaiterDelegate {

    private Waiter waiter;

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public void serviceClient(String clientName) {
        waiter.greetTo(clientName);
        waiter.serveTo(clientName);
    }
}
