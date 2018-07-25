package com.cars.server.dao;

import com.cars.shared.models.Manager;
import org.springframework.stereotype.Repository;

@Repository("managerDAO")
public class ManagerDAO extends DAO<Manager, Long> {

    public ManagerDAO() {
        super(Manager.class);
    }
}