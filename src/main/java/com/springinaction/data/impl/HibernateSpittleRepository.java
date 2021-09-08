package com.springinaction.data.impl;

import com.springinaction.common.DuplicateSpittleException;
import com.springinaction.common.Spittle;
import com.springinaction.data.SpittleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zjjfly on 2017/2/8.
 */
//@Repository
public class HibernateSpittleRepository implements SpittleRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateSpittleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        return this.sessionFactory.getCurrentSession();
    }


    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public List<Spittle> findSpittles(long max, int count) {
        List<Spittle> list = currentSession().createCriteria(Spittle.class).setMaxResults(count).list();
        return list;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public Spittle findOne(long id) {
        Spittle spittle = (Spittle) currentSession().createCriteria(Spittle.class)
                .add(Restrictions.eq("id", id))
                .list().get(0);
        return spittle;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public Spittle save(Spittle spittle) throws DuplicateSpittleException {
        Long id = (Long) currentSession().save(spittle);
        spittle.setId(id);
        return spittle;
    }
}
