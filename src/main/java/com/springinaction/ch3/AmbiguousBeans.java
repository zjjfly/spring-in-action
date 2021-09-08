package com.springinaction.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zjjfly on 2016/12/21.
 */
@Configuration
public class AmbiguousBeans {
    @Bean
    @Cold
    @Creamy
    public Dessert dessert(){
        return new IceCream();
    }
}
