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

    public List<AutoPopup> getComfortAttributesList(Long id, String modification) {
        List<AutoPopup> list = new ArrayList<>();
        AutoPopup autoPopup = new AutoPopup();
        for (Equipment e : equipmentDAO.getAll()) {
            if (id.compareTo(e.getIdAutomobile().getIdAutomobile()) == 0) {
                if (e.getNameMod().equals(modification)) {
                    autoPopup.setAttribute(e.getIdAttribute().getName());
                    autoPopup.setValue(e.getValue());
                    list.add(autoPopup);
                }
            }
        }
        return list;
    }

    @Override
    public List<String> getModification(Long id) {
        List<String> list = new ArrayList<>();
        for (Equipment e : equipmentDAO.getAll()) {
            if (e.getIdAutomobile().getIdAutomobile().equals(id)) {
                if (e.getNameMod().equals("Standard")) {
                    if (!list.contains("Standard"))
                        list.add("Standard");
                }
                if (e.getNameMod().equals("Comfort")) {
                    if (!list.contains("Comfort")) list.add("Comfort");
                }
            }
        }
        return list;
    }


}
