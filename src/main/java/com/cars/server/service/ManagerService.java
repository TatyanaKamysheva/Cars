package com.cars.server.service;

import com.cars.server.dao.ManagerDAO;
import com.cars.shared.models.Manager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private ManagerDAO<Manager> managerDAO;

    public void setManagerDAO(ManagerDAO managerDAO) {
        this.managerDAO = managerDAO;
    }

    Logger logger = Logger.getLogger(ManagerService.class);

    @Transactional
    public void save(Manager manager) {
        this.managerDAO.save(manager);
    }

    @Transactional
    public List<Manager> listManagers() {

        return this.managerDAO.list(Manager.class.getSimpleName());
    }

}
