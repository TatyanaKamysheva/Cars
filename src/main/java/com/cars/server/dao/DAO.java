package com.cars.server.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAO<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(DAO.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(T t){
        Session session = this.sessionFactory.getCurrentSession();
        session.save(t);
    }

    @SuppressWarnings("unchecked")
    public List<T> list(String className) {
        Session session = this.sessionFactory.getCurrentSession();
        List<T> list = session.createQuery("from "+className).list();
        return list;
    }
}

