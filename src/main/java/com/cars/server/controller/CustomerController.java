package com.cars.server.controller;


import com.cars.server.service.api.CustomerService;
import com.cars.shared.models.Customer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    Logger logger = Logger.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public @ResponseBody
    List<Customer> list() {
        return this.customerService.getAll();
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    @ResponseBody
    void save(@RequestBody Customer customer) throws Exception {
        logger.info(customer.toString());
        this.customerService.save(customer);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.customerService.delete(id);
    }

    @RequestMapping(value = "/customers/update", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Customer customer) throws Exception {
        this.customerService.update(customer);
    }
}
