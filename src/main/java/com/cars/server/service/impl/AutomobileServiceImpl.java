package com.cars.server.service.impl;

import com.cars.server.dao.AutomobileDAO;
import com.cars.server.service.api.AutomobileService;
import com.cars.shared.models.Automobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("automobileService")
public class AutomobileServiceImpl implements AutomobileService {

    @Autowired
    AutomobileDAO automobileDAO;

    @Autowired
    public AutomobileServiceImpl(AutomobileDAO automobileDAO) {
        this.automobileDAO = automobileDAO;
    }

    public void save(Automobile automobile) {
        automobileDAO.save(automobile);
    }

    public Automobile findById(Long id) {
        return automobileDAO.findById(id);
    }

    public void update(Automobile automobile) {
        automobileDAO.update(automobile);
    }

    public void delete(Long id) {
        automobileDAO.delete(findById(id));
    }

    public List<Automobile> getAll() {
        return automobileDAO.getAll();
    }
}
