package com.mySampleApplication.server;

import com.mySampleApplication.shared.Manager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManagerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(ManagerDAO.class);

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Manager> listManagers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Manager> managerList = session.createQuery("SELECT manager FROM Manager manager").list();
        for (Manager manager : managerList) {
            logger.info("List:" + manager);
        }
        return managerList;
    }
}

