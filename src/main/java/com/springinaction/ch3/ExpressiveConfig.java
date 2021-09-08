package com.springinaction.ch3;

import com.springinaction.ch2.BlankDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by zjjfly on 2016/12/23.
 */
@Configuration
@PropertySource("classpath:app.properties")
public class ExpressiveConfig {
    @Autowired
    Environment  env;
    @Bean
    public BlankDisc disc(){
        //getRequiredProperty会在找不到属性值的时候抛出异常
        return new BlankDisc(env.getProperty("disc.title"),env.getRequiredProperty("disc.artist"));
    }
}
