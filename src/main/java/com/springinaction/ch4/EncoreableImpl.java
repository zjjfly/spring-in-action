package com.springinaction.ch4;

import org.springframework.stereotype.Component;

/**
 * Created by zjjfly on 2016/12/26.
 */
@Component
public class EncoreableImpl implements Encoreable {
    @Override
    public void performanceEncore() {
        System.out.println("haha");
    }
}
