package com.cars.server.service.impl;

import com.cars.server.dao.EmployeeDAO;
import com.cars.server.service.api.ManagerService;
import com.cars.shared.models.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    public ManagerServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void save(Employee manager) {
        employeeDAO.save(manager);
    }

    @Override
    public Employee findById(Long id) {
        return employeeDAO.findById(id);
    }

    @Override
    public void update(Employee manager) {
        employeeDAO.update(manager);
    }

    @Override
    public void delete(Long id) {
        employeeDAO.delete(findById(id));
    }

    @Override
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    public Employee getByName(String fname, String surname) {
        return this.employeeDAO.getByName(fname, surname);
    }
}

