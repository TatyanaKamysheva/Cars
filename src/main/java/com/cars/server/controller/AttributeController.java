package com.cars.server.controller;

import com.cars.server.service.api.AttributeService;
import com.cars.shared.models.Response;
import com.cars.shared.models.entities.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/attributes", method = RequestMethod.GET)
    public @ResponseBody
    List<Attribute> list() {
        return this.attributeService.getAll();
    }

    @RequestMapping(value = "/attributes", method = RequestMethod.POST)
    @ResponseBody
    Response save(@RequestBody Attribute attribute) {
        try {
            this.attributeService.save(attribute);
            return new Response(1, "Attribute successfully saved!");
        } catch (Exception e) {
            return new Response(0, "Fail to save attribute: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/attributes/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response delete(@PathVariable("id") Long id) {
        try {
            this.attributeService.delete(id);
            return new Response(1, "Attribute successfully deleted!");
        } catch (Exception e) {
            return new Response(0, "Fail to delete attribute: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/attributes/update", method = RequestMethod.PUT)
    @ResponseBody
    public Response update(@RequestBody Attribute attribute) {
        try {
            this.attributeService.update(attribute);
            return new Response(1, "Attribute successfully updated!");
        } catch (Exception e) {
            return new Response(0, "Fail to update attribute: " + e.getMessage());
        }
    }
}

