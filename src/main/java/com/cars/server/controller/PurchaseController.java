package com.cars.server.controller;

import com.cars.server.service.api.EquipmentService;
import com.cars.server.service.api.PurchaseService;
import com.cars.shared.models.Response;
import com.cars.shared.models.entities.Equipment;
import com.cars.shared.models.entities.Purchase;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {

    private static final Logger logger = Logger.getLogger(Purchase.class);
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private EquipmentService equipmentService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/purchases", method = RequestMethod.GET)
    public @ResponseBody
    List<Purchase> list() {
        List<Purchase> list = this.purchaseService.getAll();
        return list;
    }

    @RequestMapping(value = "/purchases", method = RequestMethod.POST)
    @ResponseBody
    Response save(@RequestBody Purchase purchase) {
        try {
            Equipment equipment = this.equipmentService.getAvailability(purchase.getAutomobile().getAutomobileId(),
                    purchase.getModification());
            if (!equipment.getValue().equals("0")) {
                Long count = Long.valueOf(equipment.getValue());
                count--;
                equipment.setValue(count.toString());
                try {
                    this.purchaseService.save(purchase);
                    this.equipmentService.update(equipment);
                    return new Response(1, "Purchase successfully saved!");
                } catch (Exception e) {
                    return new Response(0, e.getMessage());
                }
            } else return new Response(-1, "Automobile is not available!");
        } catch (Exception e) {
            return new Response(-2, e.getMessage());
        }
    }

    @RequestMapping(value = "/purchases/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response delete(@PathVariable("id") Long id) {
        try {
            this.purchaseService.delete(id);
            return new Response(1, "Customer successfully deleted!");
        } catch (Exception e) {
            return new Response(0, e.getMessage());
        }
    }

    @RequestMapping(value = "/purchases/update", method = RequestMethod.PUT)
    @ResponseBody
    public Response update(@RequestBody Purchase purchase) {
        try {
            this.purchaseService.update(purchase);
            return new Response(1, "Customer successfully updated!");
        } catch (Exception e) {
            return new Response(0, e.getMessage());
        }
    }
}
