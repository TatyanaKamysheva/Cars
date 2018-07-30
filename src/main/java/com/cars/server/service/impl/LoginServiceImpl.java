package com.cars.server.service.impl;

import com.cars.server.dao.UserDAO;
import com.cars.shared.models.Roles;
import com.cars.shared.models.User;
import com.cars.shared.models.UserLoginInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl {

    Logger logger = Logger.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    public UserLoginInfo loginUser(String login, String password) {
        logger.info(login + password);
        User user = userDAO.findUserByLogin(login);
        logger.info(user);
        if (!user.getLogin().isEmpty()) {
            String passwordDB = user.getPassword();
            if (password.equals(passwordDB)) {
                UserLoginInfo userLoginInfo = new UserLoginInfo();
                userLoginInfo.setEmployeeId(user.getManager().getIdManager());
                userLoginInfo.setEmployeeFirstName(user.getManager().getSurname());

                if (user.getManager().getRole().equals("Admin")) {
                    userLoginInfo.setRole(Roles.Admin);
                } else if (user.getManager().getRole().equals("Supervisor")) {
                    userLoginInfo.setRole(Roles.Supervisor);
                } else {
                    userLoginInfo.setRole(Roles.Seller);
                }
                return userLoginInfo;
            }
        }

        return null;
    }

    public User findUserByLogin(String login) {
        return userDAO.findUserByLogin(login);
    }

    public void update(User user) {
        userDAO.update(user);
    }

    public void save(User user) {
        logger.info("Save" + user.toString());
        userDAO.save(user);
    }

    public User findById(Long id) {
        return userDAO.findById(id);
    }

    public void delete(Long id) {
        User user = userDAO.findById(id);
        userDAO.delete(user);
    }
}
