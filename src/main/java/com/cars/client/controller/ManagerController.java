package com.cars.client.controller;

import com.cars.server.service.ManagerService;
import com.cars.shared.models.Manager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@ImportResource("/WEB-INF/dispatcher-servlet.xml")
@RestController
public class ManagerController {
    Logger logger = Logger.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;

    @Autowired
    @Qualifier(value = "managerService")
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<Manager> list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return this.managerService.listManagers();
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Accept=application/json")
    public void save(@RequestBody Manager manager)
            throws ServletException, IOException {
        this.managerService.save(manager);


    }

}
