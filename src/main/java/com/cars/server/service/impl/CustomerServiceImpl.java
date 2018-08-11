package com.cars.server.service.impl;

import com.cars.server.dao.CustomerDAO;
import com.cars.server.service.api.CustomerService;
import com.cars.shared.models.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public void save(Customer customer) {
        customerDAO.save(customer);
    }

    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public Customer findById(Long id) {
        return customerDAO.findById(id);
    }

    @Override
    public void update(Customer customer) {
        customerDAO.update(customer);
    }

    @Override
    public void delete(Long id) {
        customerDAO.delete(findById(id));
    }

    @Override
    public List<Customer> getAll() {
        return customerDAO.getAll();
    }
}
