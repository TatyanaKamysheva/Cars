package com.mySampleApplication.client;


import com.mySampleApplication.server.ManagerDAO;
import com.mySampleApplication.shared.Manager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class Controller {
    private static final Logger logger = Logger.getLogger(ManagerDAO.class);
    //@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ManagerDAO managerDAO;

    @RequestMapping(value = "loadInfo", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Manager handleRequest(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        Manager manager = new Manager();
        manager.setFullName("ann");
        manager.setIdManager(1);
        return manager;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<Manager> handleRequest1(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException {
        logger.info("WE HERE!");
        return this.managerDAO.listManagers();
    }

    public void setManagerDAO(ManagerDAO managerDAO) {
        this.managerDAO = managerDAO;
    }
}

