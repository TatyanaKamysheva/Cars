package com.cars.server.controller;

import com.cars.server.service.api.EquipmentService;
import com.cars.shared.models.AutoPopup;
import com.cars.shared.models.Equipment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

@RestController
public class EquipmentController {
    Logger logger = Logger.getLogger(EquipmentController.class);
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
    void save(@RequestBody Equipment equipment) throws Exception {
        this.equipmentService.save(equipment);
    }

    @RequestMapping(value = "/equipment/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.equipmentService.delete(id);
    }

    @RequestMapping(value = "/equipment/update", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Equipment equipment) throws Exception {
        this.equipmentService.update(equipment);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/equipment/popup_{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<AutoPopup> listPopup(@PathVariable("id") Long id) {
        return this.equipmentService.getComfortAttributesList(id);
    }

}
