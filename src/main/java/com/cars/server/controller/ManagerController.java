package com.cars.server.controller;

import com.cars.server.service.api.ManagerService;
import com.cars.server.service.impl.LoginServiceImpl;
import com.cars.shared.models.Manager;
import com.cars.shared.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    Logger logger = Logger.getLogger(ManagerController.class);

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/managers", method = RequestMethod.GET)
    public @ResponseBody
    List<Manager> listManager() {
        return this.managerService.getAll();
    }

    @Autowired
    private LoginServiceImpl loginService;

    @RequestMapping(value = "/managers", method = RequestMethod.POST)
    @ResponseBody
    void save(@RequestBody Manager manager) throws Exception {
        this.managerService.save(manager);
        String login = manager.getFirstName() + manager.getSurname();
        String password = "934b535800b1cba8f96a5d72f72f1611";
        User user = new User(login, password);
        this.loginService.save(user);

    }

    @RequestMapping(value = "/managers/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.managerService.delete(id);
        this.loginService.delete(id);
    }

    @RequestMapping(value = "/managers/update", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Manager manager) throws Exception {
        this.managerService.update(manager);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/managers/get_{id}", method = RequestMethod.GET)
    public @ResponseBody
    Manager getManager(@PathVariable("id") Long id) {
        return this.managerService.findById(id);
    }
}
