package com.springinaction.ch3;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Created by zjjfly on 2016/12/21.
 */
@Component("cake")
@Primary
public class Cake implements Dessert {
}
