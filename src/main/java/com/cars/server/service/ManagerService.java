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
    private Logger logger = Logger.getLogger(ManagerService.class);
    @Autowired
    private ManagerDAO managerDAO;

    public void setManagerDAO(ManagerDAO managerDAO) {
        this.managerDAO = managerDAO;
    }

    @Transactional
    public void save(Manager manager) {
        logger.info("saving..." + manager.getIdManager() + " " + manager.getFullName());
        manager.setIdManager(null);
        this.managerDAO.save(manager);
    }

    @Transactional
    public void update(Manager manager) {
        logger.info(manager.getIdManager());
        logger.info(manager.getFullName());
        logger.info(manager.getSalary());
        logger.info(manager.getPhone());

        this.managerDAO.update(manager);
    }

    @Transactional
    public void delete(Integer id) {
        logger.info(id + " - deleting...");
        this.managerDAO.delete(id);
    }

    @Transactional
    public List<Manager> listManagers() {
        for (Manager m : this.managerDAO.list()) {
            logger.info(m.toString());
        }
        return this.managerDAO.list();
    }

}
