package com.cars.server.service.api;

import com.cars.shared.models.Attribute;

import java.util.List;

public interface AttributeService {
    void save(Attribute attribute) throws Exception;

    Attribute findById(Long id);

    void update(Attribute attribute) throws Exception;

    void delete(Long id) throws Exception;

    List<Attribute> getAll();
}
