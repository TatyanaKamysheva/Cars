package com.cars.server.controller;

import com.cars.server.service.api.EquipmentService;
import com.cars.shared.models.AttributePopup;
import com.cars.shared.models.Modification;
import com.cars.shared.models.Response;
import com.cars.shared.models.entities.Equipment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EquipmentController {

    private static final Logger logger = Logger.getLogger(EquipmentController.class);

    @Autowired
    private EquipmentService equipmentService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/equipment", method = RequestMethod.GET)
    public @ResponseBody
    List<Equipment> listManager() {
        return this.equipmentService.getAll();
    }

    @RequestMapping(value = "/equipment", method = RequestMethod.POST)
    @ResponseBody
    Response save(@RequestBody Equipment equipment) {
        try {
            this.equipmentService.save(equipment);
            return new Response(1, "Equipment successfully saved!");
        } catch (Exception e) {
            return new Response(0, e.getMessage());
        }
    }

    @RequestMapping(value = "/equipment/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response delete(@PathVariable("id") Long id) {
        try {
            this.equipmentService.delete(id);
            return new Response(1, "Equipment successfully deleted!");
        } catch (Exception e) {
            return new Response(0, e.getMessage());
        }
    }

    @RequestMapping(value = "/equipment/update", method = RequestMethod.PUT)
    @ResponseBody
    public Response update(@RequestBody Equipment equipment) {
        try {
            this.equipmentService.update(equipment);
            return new Response(1, "Equipment successfully updated!");
        } catch (Exception e) {
            return new Response(0, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/equipment/popup_{id}_{modification}", method = RequestMethod.GET)
    public @ResponseBody
    List<Equipment> listPopup(@PathVariable("id") Long id, @PathVariable("modification") String modification) {
        return this.equipmentService.getAttributeList(id, modification);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/equipment_{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<Modification> listModifications(@PathVariable("id") Long id) {
        return this.equipmentService.listModifications(id);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/equipment/filter/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<Equipment> filter(@PathVariable("id") Long id) {
        return this.equipmentService.filter(id);
    }
}
