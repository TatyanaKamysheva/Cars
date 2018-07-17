package com.cars.server.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DAO<T> {

    @Autowired
    public SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(DAO.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(T t) {
        Session session = this.sessionFactory.getCurrentSession();
        logger.info(t.toString());
        session.save(t);
    }

    public void delete(Integer id) {
    }

    @SuppressWarnings("unchecked")
    public List<T> list() {
        return new ArrayList<>();
    }

    public void update(T t) {
        logger.info((t.toString()));
        Session session = this.sessionFactory.getCurrentSession();
        session.update(t);
    }
}

