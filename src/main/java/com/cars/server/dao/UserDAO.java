package com.cars.server.dao;

import com.cars.shared.models.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;


@Repository("userDAO")
public class UserDAO extends DAO<User, Long> {

    private Logger logger = Logger.getLogger(UserDAO.class);

    public UserDAO() {
        super(User.class);
    }

    public User findUserByLoginAndPassword(String login, String password) {
        Query q = getCurrentSession()
                .createQuery("from User " +
                        "where userLogin = :login " +
                        "and userPassword = :password")
                .setParameter("login", login)
                .setParameter("password", password);
        try {
            return (User) q.uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User findUserByLogin(String login) {
        logger.info(login);
        Query q = getCurrentSession()
                .createQuery("from User " +
                        "where login = :login")
                .setParameter("login", login);
        logger.info("Query" + q.toString());
        try {
            return (User) q.uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
