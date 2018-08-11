package com.cars.server.service.api;

import com.cars.shared.models.entities.Purchase;

import java.util.List;

public interface PurchaseService {
    void save(Purchase purchase) throws Exception;

    Purchase findById(Long id);

    void update(Purchase purchase) throws Exception;

    void delete(Long id) throws Exception;

    List<Purchase> getAll();
}
