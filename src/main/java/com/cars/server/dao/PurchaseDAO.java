package com.cars.server.dao;

import com.cars.shared.models.Purchase;
import org.springframework.stereotype.Repository;


@Repository("purchaseDAO")
public class PurchaseDAO extends DAO<Purchase, Long> {
    public PurchaseDAO() {
        super(Purchase.class);
    }
}

