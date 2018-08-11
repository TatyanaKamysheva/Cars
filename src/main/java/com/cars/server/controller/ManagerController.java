package com.cars.server.controller;

import com.cars.server.service.api.ManagerService;
import com.cars.server.service.impl.LoginServiceImpl;
import com.cars.shared.models.Response;
import com.cars.shared.models.entities.Employee;
import com.cars.shared.models.entities.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    private static final Logger logger = Logger.getLogger(ManagerController.class);

    @Autowired
    private LoginServiceImpl loginService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/managers", method = RequestMethod.GET)
    public @ResponseBody
    List<Employee> listManager() {
        return this.managerService.getAll();
    }

    @RequestMapping(value = "/managers", method = RequestMethod.POST)
    @ResponseBody
    Response save(@RequestBody Employee manager) {
        try {
            this.managerService.save(manager);
            String login = manager.getFirstName() + manager.getSurname();
            String password = "934b535800b1cba8f96a5d72f72f1611";
            User user = new User(login, password);
            try {
                this.loginService.save(user);
                return new Response(1, "Employee saved!");
            } catch (Exception e) {
                return new Response(0, e.getMessage());
            }
        } catch (Exception e) {
            return new Response(-1, e.getMessage());
        }
    }

    @RequestMapping(value = "/managers/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response delete(@PathVariable("id") Long id) {
        try {
            this.managerService.delete(id);
            return new Response(1, "Employee successfully deleted!");
        } catch (Exception e) {
            return new Response(0, e.getMessage());
        }
    }

    @RequestMapping(value = "/managers/update", method = RequestMethod.PUT)
    @ResponseBody
    public Response update(@RequestBody Employee manager) {
        try {
            this.managerService.update(manager);
            return new Response(1, "Employee successfully updated!");
        } catch (Exception e) {
            return new Response(0, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/managers/get_{id}", method = RequestMethod.GET)
    public @ResponseBody
    Employee getManager(@PathVariable("id") Long id) {
        return this.managerService.findById(id);
    }
}
