package com.springinaction.data.impl;

import com.springinaction.common.DuplicateSpittleException;
import com.springinaction.common.Spittle;
import com.springinaction.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by zjjfly on 2017/2/11.
 */
@Repository(value = "jSpittleRepository")
@Transactional
public class JpaSpittleRepository implements SpittleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        Query query = em.createQuery("select s from Spittle s");
        query.setMaxResults(count);
        return query.getResultList();
    }

    @Override
    public Spittle findOne(long id) {
        return em.find(Spittle.class, id);
    }

    @Override
    public Spittle save(Spittle spittle) throws DuplicateSpittleException {
        Spittle merge = em.merge(spittle);
        return merge;
    }
}
