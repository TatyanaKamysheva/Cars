package com.cars.server.service.api;

import com.cars.shared.models.entities.Employee;

import java.util.List;

public interface ManagerService {
    void save(Employee manager) throws Exception;

    Employee findById(Long id);

    void update(Employee manager) throws Exception;

    void delete(Long id) throws Exception;

    List<Employee> getAll();

    Employee getByName(String fname, String surname);
}
