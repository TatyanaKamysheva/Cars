package com.cars.server.service.api;

import com.cars.shared.models.UserLoginInfo;
import com.cars.shared.models.entities.User;

public interface LoginService {
    UserLoginInfo loginUser(String login, String password);

    User findUserByLogin(String login);

    void update(User user);

    void save(User user);

    User findById(Long id);

    void delete(Long id);
}

