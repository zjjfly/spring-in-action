package com.springinaction.data.impl;

import com.springinaction.common.Spitter;
import com.springinaction.data.SpitterRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by zjjfly on 2017/2/1.
 */
//@Repository
public class HibernateSpitterRepository implements SpitterRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateSpitterRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public Spitter save(Spitter spitter) {
        Serializable id = currentSession().save(spitter);
        return new Spitter((Long) id,spitter.getUsername(),spitter.getPassword(),spitter.getFirstName(),spitter.getLastName(),spitter.getEmail());
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public Spitter findByUsername(String username) {
       return (Spitter) currentSession().createCriteria(Spitter.class)
        .add(Restrictions.eq("username",username))
        .list().get(0);
    }
}
