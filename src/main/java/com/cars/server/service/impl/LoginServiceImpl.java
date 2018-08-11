package com.cars.server.service.impl;

import com.cars.server.dao.UserDAO;
import com.cars.server.service.api.LoginService;
import com.cars.shared.Roles;
import com.cars.shared.models.UserLoginInfo;
import com.cars.shared.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {


    @Autowired
    private UserDAO userDAO;

    public UserLoginInfo loginUser(String login, String password) {
        User user = userDAO.findUserByLogin(login);
        if (user == null) return null;
        if (!user.getLogin().isEmpty()) {
            String passwordDB = user.getPassword();
            if (password.equals(passwordDB)) {
                UserLoginInfo userLoginInfo = new UserLoginInfo();
                userLoginInfo.setUserId(user.getEmployee().getEmployeeId());
                userLoginInfo.setFirstName(user.getEmployee().getFirstName());
                userLoginInfo.setSurname(user.getEmployee().getSurname());
                switch (user.getEmployee().getRole()) {
                    case "Admin":
                        userLoginInfo.setRole(Roles.Admin);
                        break;
                    case "Supervisor":
                        userLoginInfo.setRole(Roles.Supervisor);
                        break;
                    default:
                        userLoginInfo.setRole(Roles.Seller);
                        break;
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
