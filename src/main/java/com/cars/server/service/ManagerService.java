package com.cars.server.service;

import com.cars.server.dao.DAO;
import com.cars.shared.models.Manager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private DAO<Manager> DAO;

    public void setDAO(DAO DAO) {
        this.DAO = DAO;
    }

    Logger logger = Logger.getLogger(ManagerService.class);

    @Transactional
    public void save(Manager manager) {
        this.DAO.save(manager);
    }

    @Transactional
    public List<Manager> listManagers() {

        return this.DAO.list(Manager.class.getSimpleName());
    }

}
