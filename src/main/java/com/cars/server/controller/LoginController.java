package com.cars.server.controller;

import com.cars.server.service.impl.LoginServiceImpl;
import com.cars.shared.models.User;
import com.cars.shared.models.UserLoginInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private LoginServiceImpl loginService;

    @RequestMapping(value = "/login_{login}_{password}", method = RequestMethod.GET)
    public
    @ResponseBody
    UserLoginInfo loginUser(@PathVariable("login") String login, @PathVariable("password") String password) {
        logger.info(login + "  " + password);
        return loginService.loginUser(login, password);
    }

    @RequestMapping(value = "/user/get_{login}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUser(@PathVariable("login") String login) {
        return loginService.findUserByLogin(login);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody User user) {
        this.loginService.update(user);
    }
}