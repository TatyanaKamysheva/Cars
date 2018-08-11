package com.cars.server.service.impl;

import com.cars.server.dao.PurchaseDAO;
import com.cars.server.service.api.PurchaseService;
import com.cars.shared.models.entities.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseDAO purchaseDAO;

    @Autowired
    public PurchaseServiceImpl(PurchaseDAO purchaseDAO) {
        this.purchaseDAO = purchaseDAO;
    }

    @Override
    public void save(Purchase purchase) {
        purchaseDAO.save(purchase);
    }

    @Override
    public Purchase findById(Long id) {
        return purchaseDAO.findById(id);
    }

    @Override
    public void update(Purchase purchase) {
        purchaseDAO.update(purchase);
    }

    @Override
    public void delete(Long id) {
        purchaseDAO.delete(findById(id));
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseDAO.getAll();
    }
}
