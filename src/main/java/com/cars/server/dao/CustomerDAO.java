package com.cars.server.dao;

import com.cars.shared.models.entities.Customer;
import org.springframework.stereotype.Repository;

@Repository("customerDAO")
public class CustomerDAO extends DAO<Customer, Long> {

    public CustomerDAO() {
        super(Customer.class);
    }
}



