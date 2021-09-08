package com.springinaction.config;

import com.springinaction.ch2.CompactDisc;
import com.springinaction.ch2.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zjjfly on 2016/12/19.
 */
//@Configuration
public class CDConfig {
    @Bean
    public CompactDisc compactDisc() {
        return new SgtPeppers();
    }
}
