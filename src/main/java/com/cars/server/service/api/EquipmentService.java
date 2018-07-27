package com.cars.server.service.api;

import com.cars.shared.models.AutoPopup;
import com.cars.shared.models.Equipment;

import java.util.List;

public interface EquipmentService {
    void save(Equipment equipment) throws Exception;

    Equipment findById(Long id);

    void update(Equipment equipment) throws Exception;

    void delete(Long id) throws Exception;

    List<Equipment> getAll();

    List<AutoPopup> getComfortAttributesList(Long id, String modification);

    List<String> getModification(Long id);
}
