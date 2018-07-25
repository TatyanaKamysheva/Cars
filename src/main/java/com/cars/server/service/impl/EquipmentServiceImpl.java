package com.cars.server.service.impl;


import com.cars.server.dao.EquipmentDAO;
import com.cars.server.service.api.EquipmentService;
import com.cars.shared.models.AutoPopup;
import com.cars.shared.models.Equipment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("equipmentService")
public class EquipmentServiceImpl implements EquipmentService {

    Logger logger = Logger.getLogger(EquipmentServiceImpl.class);
    @Autowired
    private EquipmentDAO equipmentDAO;

    @Autowired
    public EquipmentServiceImpl(EquipmentDAO equipmentDAO) {
        this.equipmentDAO = equipmentDAO;
    }

    public void save(Equipment equipment) {
        equipmentDAO.save(equipment);
    }

    public Equipment findById(Long id) {
        return equipmentDAO.findById(id);
    }

    public void update(Equipment equipment) {
        equipmentDAO.update(equipment);
    }

    public void delete(Long id) {
        equipmentDAO.delete(findById(id));
    }

    public List<Equipment> getAll() {
        return equipmentDAO.getAll();
    }

    public List<AutoPopup> getComfortAttributesList(Long id) {
        logger.info(id);
        List<AutoPopup> list = new ArrayList<>();
        AutoPopup autoPopup = new AutoPopup();
        for (Equipment e : equipmentDAO.getAll()) {
            if (id.equals(e.getIdAutomobile()) && e.getNameMod().equals("Comfort")) {
                autoPopup.setAttribute(e.getIdAttribute().getName());
                autoPopup.setValue(e.getValue());
                list.add(autoPopup);
            }
        }
        return list;
    }
}
