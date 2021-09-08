package com.springinaction.data.impl;

import com.springinaction.common.Spitter;
import com.springinaction.data.SpitterRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by zjjfly on 2017/2/9.
 */
@Repository(value = "jSpitterRepository")
@Transactional
public class JpaSpitterRepository implements SpitterRepository {
//使用EntityManagerFactory每次都要调用createEntityManager()方法，不推荐
//    @PersistenceUnit
//    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Spitter save(Spitter spitter) {
       return em.merge(spitter);
    }

    @Override
    public Spitter findByUsername(String username) {
        Query query = em.createQuery("select s from Spitter s where s.name=?1");
        query.setParameter(1,username);
        Spitter result = (Spitter) query.getResultList().get(0);
        return result;
    }
}
