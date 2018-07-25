package com.cars.server.controller;

import com.cars.server.service.api.PurchaseService;
import com.cars.shared.models.Purchase;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {

    Logger logger = Logger.getLogger(Purchase.class);
    @Autowired
    private PurchaseService purchaseService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/purchases", method = RequestMethod.GET)
    public @ResponseBody
    List<Purchase> list() {
        return this.purchaseService.getAll();
    }

    @RequestMapping(value = "/purchases", method = RequestMethod.POST)
    @ResponseBody
    void save(@RequestBody Purchase purchase) throws Exception {
        this.purchaseService.save(purchase);
    }

    @RequestMapping(value = "/purchases/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.purchaseService.delete(id);
    }

    @RequestMapping(value = "/purchases/update", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Purchase purchase) throws Exception {
        this.purchaseService.update(purchase);
    }
}
