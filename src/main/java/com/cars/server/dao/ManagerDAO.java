package com.cars.server.dao;

import com.cars.shared.models.Manager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public class ManagerDAO<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(ManagerDAO.class);

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
        //List<T> list = session.createQuery("from Manager").list();
        logger.info(className);
        List<T> list = session.createQuery("from "+className).list();
        return list;
    }
}

