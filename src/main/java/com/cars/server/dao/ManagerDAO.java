package com.cars.server.dao;

import com.cars.shared.models.Manager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManagerDAO extends DAO<Manager> {
    private Logger logger = Logger.getLogger(ManagerDAO.class);

    @Override
    public void delete(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        Manager manager = (Manager) session.get(Manager.class, Long.valueOf(id));
        logger.info(id + " - deleting...");
        if (null != manager) {
            session.delete(manager);
            logger.info(manager.toString() + " - deleted!");
        }
    }

    @Override
    public List<Manager> list() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Manager>) session.createQuery("from " + Manager.class.getSimpleName()).list();
    }
}
