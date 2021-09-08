package com.springinaction.ch4;

import org.springframework.stereotype.Component;

/**
 *
 * @author zjjfly
 * @date 2016/12/25
 */
@Component
public class PerformanceImpl implements Performance {
    @Override
    public void perform() {
        System.out.println("oh~");
    }
}
