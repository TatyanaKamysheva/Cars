package com.cars.server.dao;

import com.cars.shared.models.AutoPopup;
import com.cars.shared.models.Equipment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("equipmentDAO")
public class EquipmentDAO extends DAO<Equipment, Long> {
    public EquipmentDAO() {
        super((Equipment.class));
    }
}
