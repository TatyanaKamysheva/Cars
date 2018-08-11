package com.cars.server.service.impl;


import com.cars.server.dao.EquipmentDAO;
import com.cars.server.service.api.EquipmentService;
import com.cars.shared.models.Modification;
import com.cars.shared.models.entities.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


@Service("equipmentService")
public class EquipmentServiceImpl implements EquipmentService {

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

    @Override
    public List<Equipment> filter(Long id) {
        return equipmentDAO.listEquipmentById(id);
    }

    public List<Equipment> getAttributeList(Long id, String modification) {
        return equipmentDAO.listEquipmentByModification(id, modification);
    }


    @Override
    public List<Modification> listModifications(Long id) {
        List<Modification> list = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (Equipment e : equipmentDAO.listEquipmentById(id)) {
            set.add(e.getModificationName());
        }
        for (String aSet : set) {
            list.add(new Modification(aSet));
        }
        return list;
    }

    @Override
    public Equipment getAvailability(Long id, String modification) {
        for (Equipment e : equipmentDAO.getAll()) {
            if (e.getModificationName().equals(modification)) {
                if (e.getAttribute().getName().equals("Availability")) {
                    if (Objects.equals(e.getAutomobile().getAutomobileId(), id)) {
                        return e;
                    }
                }
            }
        }
        return null;
    }
}