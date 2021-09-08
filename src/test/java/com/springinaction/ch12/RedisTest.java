package com.springinaction.ch12;

import com.springinaction.common.Spitter;
import com.springinaction.config.DataSourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

/**
 * Created by zjjfly on 2017/2/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
public class RedisTest {
    @Autowired
    RedisOperations<String, Spitter> redisOperations;

    @Test
    public void valueOps() throws Exception {
        redisOperations.opsForValue().set("a", new Spitter("11", "dad", "da", "da"));
        System.out.println(redisOperations.opsForValue().get("a"));
    }

    @Test
    public void listOps() throws Exception {
        BoundListOperations<String, Spitter> cart = redisOperations.boundListOps("cart");
        cart.rightPush(new Spitter("1", "dad", "da", "da"));
        cart.rightPush(new Spitter("2", "dad", "da", "da"));
        cart.rightPush(new Spitter("3", "dad", "da", "da"));
        List<Spitter> spitters = cart.range(0, 2);
        spitters.forEach(System.out::println);
    }

    @Test
    public void setOps() throws Exception {
        redisOperations.opsForSet().add("w1", new Spitter("1", "dad", "da", "da"));
        redisOperations.opsForSet().add("w1", new Spitter("3", "dad", "da", "da"));
        redisOperations.opsForSet().add("w2", new Spitter("1", "dad", "da", "da"));
        redisOperations.opsForSet().add("w2", new Spitter("2", "dad", "da", "da"));
        Set<Spitter> difference = redisOperations.opsForSet().difference("w1", "w2");//求差异
        System.out.println("Difference of w1 and w2:");
        difference.forEach(System.out::println);
        Set<Spitter> union = redisOperations.opsForSet().union("w1", "w2");//求并集
        System.out.println("Union of w1 and w2:");
        union.forEach(System.out::println);
        Set<Spitter> insect = redisOperations.opsForSet().intersect("w1", "w2");//求交集
        System.out.println("Intersection of w1 and w2:");
        insect.forEach(System.out::println);
        redisOperations.opsForSet().remove("w1",new Spitter("1", "dad", "da", "da"));//删除
        System.out.println("After remove a spitter,intersection of w1 and w2:");
        System.out.println(redisOperations.opsForSet().intersect("w1", "w2"));
        System.out.println("Random get a spitter from the Set w2:");
        System.out.println(redisOperations.opsForSet().randomMember("w2"));//随机获取
    }
}
