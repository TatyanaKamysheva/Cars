package com.cars.client.controller;

import com.cars.server.service.CustomerService;
import com.cars.shared.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ImportResource("/WEB-INF/dispatcher-servlet.xml")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    @Qualifier(value = "customerService")
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/customer/list")
    public @ResponseBody
    List<Customer> list() {
        return this.customerService.listCustomers();
    }


}