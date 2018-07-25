package com.cars.server.dao;

import com.cars.shared.models.Attribute;
import org.springframework.stereotype.Repository;

@Repository("attributeDAO")
public class AttributeDAO extends DAO<Attribute, Long> {

    public AttributeDAO() {
        super(Attribute.class);
    }
}