package com.cars.server.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Transactional(readOnly = true, value = "transactionManager")
public abstract class DAO<E, I extends Serializable> {

    protected Class<E> entityClass;

    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public DAO(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired()
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Transactional
    public void save(E entity) {
        getCurrentSession().save(entity);
        getCurrentSession().flush();
    }

    @Transactional
    public E findById(I id) {
        return (E) getCurrentSession().get(entityClass, id);
    }

    @Transactional
    public E update(E entity) {
        getCurrentSession().update(entity);
        getCurrentSession().flush();
        return entity;
    }

    @Transactional
    public void delete(E entity) {
        getCurrentSession().delete(entity);
    }


    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<E> list = (List<E>) session.createQuery("from " + entityClass.getSimpleName()).list();
        return list;
    }
}


