package com.cars.server.service.api;

import com.cars.shared.models.entities.Customer;

import java.util.List;

public interface CustomerService {
    void save(Customer customer) throws Exception;

    Customer findById(Long id);

    void update(Customer customer) throws Exception;

    void delete(Long id) throws Exception;

    List<Customer> getAll();
}
