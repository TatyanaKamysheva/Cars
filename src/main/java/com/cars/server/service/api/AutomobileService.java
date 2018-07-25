package com.cars.server.service.api;

import com.cars.shared.models.Automobile;

import java.util.List;

public interface AutomobileService {
    void save(Automobile automobile) throws Exception;

    Automobile findById(Long id);

    void update(Automobile automobile) throws Exception;

    void delete(Long id) throws Exception;

    List<Automobile> getAll();
}
