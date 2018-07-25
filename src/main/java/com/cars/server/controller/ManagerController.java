package com.cars.server.controller;

import com.cars.server.service.api.ManagerService;
import com.cars.shared.models.Manager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/managers", method = RequestMethod.GET)
    public @ResponseBody
    List<Manager> listManager() {
        return this.managerService.getAll();
    }

    @RequestMapping(value = "/managers", method = RequestMethod.POST)
    @ResponseBody
    void save(@RequestBody Manager manager) throws Exception {
        this.managerService.save(manager);
    }

    @RequestMapping(value = "/managers/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.managerService.delete(id);
    }

    @RequestMapping(value = "/managers/update", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Manager manager) throws Exception {
        this.managerService.update(manager);
    }
}
