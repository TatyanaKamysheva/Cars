package com.cars.server.service.impl;

import com.cars.server.dao.AttributeDAO;
import com.cars.server.service.api.AttributeService;
import com.cars.shared.models.entities.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("attributeService")
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    AttributeDAO attributeDAO;

    @Autowired
    public AttributeServiceImpl(AttributeDAO attributeDAO) {
        this.attributeDAO = attributeDAO;
    }

    public void save(Attribute attribute) {
        attributeDAO.save(attribute);
    }

    public Attribute findById(Long id) {
        return attributeDAO.findById(id);
    }

    public void update(Attribute attribute) {
        attributeDAO.update(attribute);
    }

    public void delete(Long id) {
        attributeDAO.delete(findById(id));
    }

    public List<Attribute> getAll() {
        return attributeDAO.getAll();
    }
}
