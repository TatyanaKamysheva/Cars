package com.cars.server.dao;

import com.cars.shared.models.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository("userDAO")
public class UserDAO extends DAO<User, Long> {

    private static final Logger logger = Logger.getLogger(UserDAO.class);

    public UserDAO() {
        super(User.class);
    }

    public User findUserByLogin(String login) {
        Query q = getCurrentSession()
                .createQuery("from User " +
                        "where login = :login")
                .setParameter("login", login);

        try {
            return (User) q.uniqueResult();
        } catch (Exception e) {
            logger.info("exception " + e.getMessage());
            return null;
        }
    }
}
