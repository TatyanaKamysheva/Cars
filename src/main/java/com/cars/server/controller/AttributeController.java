package com.cars.server.controller;

import com.cars.server.service.api.AttributeService;
import com.cars.shared.models.Attribute;
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
    void save(@RequestBody Attribute attribute) throws Exception {
        this.attributeService.save(attribute);
    }

    @RequestMapping(value = "/attributes/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.attributeService.delete(id);
    }

    @RequestMapping(value = "/attributes/update", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Attribute attribute) throws Exception {
        this.attributeService.update(attribute);
    }
}

