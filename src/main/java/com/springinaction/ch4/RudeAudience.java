package com.springinaction.ch4;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by zjjfly on 2016/12/26.
 */
public class RudeAudience {

    public void stand() {
        System.out.println("the rude:stand on seats");
    }

    public void shout() {
        System.out.println("the rude:fuck you!!!");
    }

    public void demandRefund() {
        System.out.println("the rude:hit the artist ");
    }

    //环绕通知
    public void watchPerformance(ProceedingJoinPoint joinPoint){
        System.out.println("the rude:start the fucking show");
        try {
            joinPoint.proceed();
            System.out.println("the rude:a piece of shit");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
