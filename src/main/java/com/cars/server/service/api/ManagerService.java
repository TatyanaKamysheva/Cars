package com.cars.server.service.api;

import com.cars.shared.models.Manager;

import java.util.List;

public interface ManagerService {
    void save(Manager manager) throws Exception;

    Manager findById(Long id);

    void update(Manager manager) throws Exception;

    void delete(Long id) throws Exception;

    List<Manager> getAll();
}
