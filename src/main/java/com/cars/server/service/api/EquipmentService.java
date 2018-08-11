package com.cars.server.service.api;

import com.cars.shared.models.AttributePopup;
import com.cars.shared.models.entities.Equipment;
import com.cars.shared.models.Modification;

import java.util.List;

public interface EquipmentService {
    void save(Equipment equipment) throws Exception;

    Equipment findById(Long id);

    void update(Equipment equipment) throws Exception;

    void delete(Long id) throws Exception;

    List<Equipment> getAll();

    List<Equipment> filter(Long id);

    List<Equipment> getAttributeList(Long id, String modification);

    List<Modification> listModifications(Long id);

    Equipment getAvailability(Long id, String modification);
}
