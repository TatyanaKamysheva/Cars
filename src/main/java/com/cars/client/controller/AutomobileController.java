package com.cars.client.controller;


import com.cars.server.service.AutomobileService;
import com.cars.shared.models.Automobile;
import com.cars.shared.models.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@ImportResource("/WEB-INF/dispatcher-servlet.xml")
@RestController
public class AutomobileController {


    @Autowired
    private AutomobileService automobileService;

    @Autowired
    @Qualifier(value = "automobileService")
    public void setAutomobileService(AutomobileService automobileService) {
        this.automobileService = automobileService;
    }

    @RequestMapping(value = "/automobile/list", method = RequestMethod.GET)
    public @ResponseBody
    List<Automobile> list(HttpServletRequest request, HttpServletResponse response) {
        return this.automobileService.list();
    }
}
