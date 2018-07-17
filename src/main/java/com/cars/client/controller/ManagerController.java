package com.cars.client.controller;

import com.cars.server.service.ManagerService;
import com.cars.shared.models.Manager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ImportResource("/WEB-INF/dispatcher-servlet.xml")
@RestController
public class ManagerController {

    private Logger logger = Logger.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;

    @Autowired
    @Qualifier(value = "managerService")
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @RequestMapping(value = "/manager/list", method = RequestMethod.GET)
    public @ResponseBody
    List<Manager> list() {
        logger.info("Controller");
        return this.managerService.listManagers();
    }


    @RequestMapping(value = "/manager/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(@RequestBody Manager manager) {
        if (manager.getIdManager() == null) {
            this.managerService.save(manager);
        } else
            this.managerService.update(manager);

    }

    @RequestMapping(value = "/manager/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@RequestBody Integer id) {
        logger.info(id + " - deleting...");
        this.managerService.delete(id);
    }


    /*public Manager jsonToObject(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Manager object = objectMapper.readValue(json, Manager.class);
        return object;
    }*/

}
