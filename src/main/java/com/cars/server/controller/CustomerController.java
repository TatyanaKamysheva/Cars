package com.cars.server.controller;


import com.cars.server.service.api.CustomerService;
import com.cars.shared.models.Response;
import com.cars.shared.models.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    @ResponseBody
    Response save(@RequestBody Customer customer) {
        try {
            this.customerService.save(customer);
            return new Response(1, "Customer successfully saved!");
        } catch (Exception e) {
            return new Response(0, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public @ResponseBody
    List<Customer> list() {
        return this.customerService.getAll();
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response delete(@PathVariable("id") Long id) {
        try {
            this.customerService.delete(id);
            return new Response(1, "Customer successfully deleted!");
        } catch (Exception e) {
            return new Response(0, e.getMessage());
        }
    }

    @RequestMapping(value = "/customers/update", method = RequestMethod.PUT)
    @ResponseBody
    public Response update(@RequestBody Customer customer) {
        try {
            this.customerService.update(customer);
            return new Response(1, "Customer successfully updated!");
        } catch (Exception e) {
            return new Response(0, e.getMessage());
        }
    }
}
