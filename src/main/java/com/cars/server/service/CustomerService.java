package com.cars.server.service;

import com.cars.server.dao.CustomerDAO;
import com.cars.server.dao.DAO;
import com.cars.server.dao.ManagerDAO;
import com.cars.shared.models.Customer;
import com.cars.shared.models.Manager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerDAO customerDAO;

    @Transactional
    public void save(Customer customer) {
        this.customerDAO.save(customer);
    }

    @Transactional
    public List<Customer> listCustomers() {
        return this.customerDAO.list();
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
}
