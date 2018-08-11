package com.cars.server.controller;


import com.cars.server.service.api.AutomobileService;
import com.cars.shared.models.Response;
import com.cars.shared.models.entities.Automobile;
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
    Response save(@RequestBody Automobile automobile) {
        try {
            this.automobileService.save(automobile);
            return new Response(1, "Automobile successfully saved!");
        } catch (Exception e) {
            return new Response(0, "Fail to save auto: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/autos/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response delete(@PathVariable("id") Long id) {
        try {
            this.automobileService.delete(id);
            return new Response(1, "Automobile successfully deleted!");
        } catch (Exception e) {
            return new Response(0, "Fail to delete auto: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/autos/update", method = RequestMethod.PUT)
    @ResponseBody
    public Response update(@RequestBody Automobile automobile) {
        try {
            this.automobileService.update(automobile);
            return new Response(1, "Automobile successfully updated!");
        } catch (Exception e) {
            return new Response(0, "Fail to update auto: " + e.getMessage());
        }
    }
}
