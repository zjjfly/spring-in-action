package com.springinaction.config;

import com.springinaction.ch2.CDPlayer;
import com.springinaction.ch2.CompactDisc;
import com.springinaction.ch2.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by zjjfly on 2016/12/18.
 */
//@Configuration
//@ComponentScan(basePackages = "com.springinaction")
public class CDPlayerConfig {

    @Bean(name = "coolPlayer")
    public CDPlayer player(CompactDisc disc) {
        CDPlayer player = new CDPlayer();
        player.setCd(disc);
        return player;
    }
}
