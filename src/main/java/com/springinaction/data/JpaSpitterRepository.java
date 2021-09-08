package com.springinaction.data;

import com.springinaction.common.Spitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zjjfly on 2017/2/11.
 */
public interface JpaSpitterRepository extends JpaRepository<Spitter,Long> {
    //按照命名约定定义查询
    Spitter findByUsername(String username);

    //自定义查询
    @Query("select s from Spitter s where s.email like '%gmail.com'")
    List<Spitter> findAllGmailSpitter();
}
