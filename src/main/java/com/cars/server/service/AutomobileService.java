package com.cars.server.service;

import com.cars.server.dao.AutomobileDAO;
import com.cars.shared.models.Automobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomobileService {
    @Autowired
    private AutomobileDAO automobileDAO;

    public void setAutomobileDAO(AutomobileDAO automobileDAO) {
        this.automobileDAO = automobileDAO;
    }

    public List<Automobile> list() {
        return this.automobileDAO.list();
    }
}
