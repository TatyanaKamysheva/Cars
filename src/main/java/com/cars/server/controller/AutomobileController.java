package com.cars.server.controller;


import com.cars.server.service.api.AutomobileService;
import com.cars.shared.models.Automobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AutomobileController {

    @Autowired
    private AutomobileService automobileService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/autos", method = RequestMethod.GET)
    public @ResponseBody
    List<Automobile> list() {
        return this.automobileService.getAll();
    }

    @RequestMapping(value = "/autos", method = RequestMethod.POST)
    @ResponseBody
    void save(@RequestBody Automobile automobile) throws Exception {
        this.automobileService.save(automobile);
    }

    @RequestMapping(value = "/autos/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.automobileService.delete(id);
    }

    @RequestMapping(value = "/autos/update", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Automobile automobile) throws Exception {
        this.automobileService.update(automobile);
    }
}
