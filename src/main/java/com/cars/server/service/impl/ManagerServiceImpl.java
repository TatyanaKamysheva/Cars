package com.cars.server.service.impl;

import com.cars.server.dao.ManagerDAO;
import com.cars.server.service.api.ManagerService;
import com.cars.shared.models.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDAO managerDAO;

    @Autowired
    public ManagerServiceImpl(ManagerDAO managerDAO) {
        this.managerDAO = managerDAO;
    }

    @Override
    public void save(Manager manager) {
        managerDAO.save(manager);
    }

    @Override
    public Manager findById(Long id) {
        return managerDAO.findById(id);
    }

    @Override
    public void update(Manager manager) {
        managerDAO.update(manager);
    }

    @Override
    public void delete(Long id) {
        managerDAO.delete(findById(id));
    }

    @Override
    public List<Manager> getAll() {
        return managerDAO.getAll();
    }

    public void setManagerDAO(ManagerDAO managerDAO) {
    }

    public Manager getByName(String fname, String surname) {
        return this.managerDAO.getByName(fname, surname);
    }
}

