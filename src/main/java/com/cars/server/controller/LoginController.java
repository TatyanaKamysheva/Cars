package com.cars.server.controller;

import com.cars.server.service.api.LoginService;
import com.cars.server.service.impl.LoginServiceImpl;
import com.cars.shared.models.Response;
import com.cars.shared.models.UserLoginInfo;
import com.cars.shared.models.entities.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login_{login}_{password}", method = RequestMethod.GET)
    public
    @ResponseBody
    UserLoginInfo loginUser(@PathVariable("login") String login, @PathVariable("password") String password) {
        logger.info(loginService.loginUser(login, password).toString());
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
    public Response update(@RequestBody User user) {
        try {
            this.loginService.update(user);
            return new Response(1, "User successfully updated!");
        } catch (Exception e) {
            return new Response(0, e.getMessage());
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public Response deleteUser(@PathVariable("id") Long id) {
        try {
            this.loginService.delete(id);
            return new Response(1, "User successfully deleted!");
        } catch (Exception e) {
            return new Response(0, e.getMessage());
        }
    }
}