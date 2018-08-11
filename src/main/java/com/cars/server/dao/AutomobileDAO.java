package com.cars.server.dao;

import com.cars.shared.models.entities.Automobile;
import org.springframework.stereotype.Repository;

@Repository("automobileDAO")
public class AutomobileDAO extends DAO<Automobile, Long> {

    public AutomobileDAO() {
        super(Automobile.class);
    }
}
