package com.cars.server.dao;

import com.cars.shared.models.entities.Employee;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository("managerDAO")
public class EmployeeDAO extends DAO<Employee, Long> {

    public EmployeeDAO() {
        super(Employee.class);
    }

    public Employee getByName(String fname, String surname) {
        Query q = getCurrentSession()
                .createQuery("from Employee " +
                        "where firstName = :fname " +
                        "and surname = :surname")
                .setParameter("fname", fname)
                .setParameter("surname", surname);
        try {
            return (Employee) q.uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
