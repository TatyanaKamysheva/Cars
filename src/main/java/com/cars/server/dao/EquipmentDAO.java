package com.cars.server.dao;

import com.cars.shared.models.entities.Equipment;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("equipmentDAO")
public class EquipmentDAO extends DAO<Equipment, Long> {
    public EquipmentDAO() {
        super((Equipment.class));
    }

    public List<Equipment> listEquipmentByModification(Long id, String modification) {
        Query q = getCurrentSession()
                .createQuery("from Equipment " +
                        "where modificationName = :modification " +
                        "and automobile.automobileId = :id")
                .setParameter("modification", modification)
                .setParameter("id", id);

        try {
            return (List<Equipment>) q.list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Equipment> listEquipmentById(Long id) {
        Query q = getCurrentSession()
                .createQuery("from Equipment " +
                        "where automobile.automobileId = :id")
                .setParameter("id", id);

        try {
            return (List<Equipment>) q.list();
        } catch (Exception e) {
            return null;
        }
    }
}
