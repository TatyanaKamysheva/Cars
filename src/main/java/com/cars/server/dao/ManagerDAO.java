package com.cars.server.dao;

import com.cars.shared.models.Manager;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository("managerDAO")
public class ManagerDAO extends DAO<Manager, Long> {

    public ManagerDAO() {
        super(Manager.class);
    }

    public Manager getByName(String fname, String surname) {
        Query q = getCurrentSession()
                .createQuery("from Manager " +
                        "where firstName = :fname " +
                        "and surname = :surname")
                .setParameter("fname", fname)
                .setParameter("password", surname);
        try {
            return (Manager) q.uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
