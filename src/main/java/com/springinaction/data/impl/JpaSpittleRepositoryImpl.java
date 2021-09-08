package com.springinaction.data.impl;

import com.springinaction.common.Spittle;
import com.springinaction.data.SpittleWepper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by zjjfly on 2017/2/12.
 */
//混合使用自定义查询
public class JpaSpittleRepositoryImpl implements SpittleWepper {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Spittle> badSpittle() {
        Query query = entityManager.createQuery("select s from Spittle s where s.message like '%fuck%'");
        return query.getResultList();
    }
}
