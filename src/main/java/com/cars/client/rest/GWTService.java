package com.cars.client.rest;

import com.google.gwt.core.client.GWT;
import com.cars.shared.models.Manager;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.RestServiceProxy;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Path("/service")
public interface GWTService extends RestService {
    Logger logger = Logger.getLogger("NameOfYourLogger");
    class Util{
        private static GWTService service;
        public static GWTService getService(){
            if(service==null){
                service= GWT.create(GWTService.class);
                logger.log(Level.SEVERE, "this");
            }
            Resource resource=new Resource(GWT.getModuleBaseURL()+"service");
            ((RestServiceProxy)service).setResource(resource);
            logger.log(Level.SEVERE, "message should get logged");
            return service;
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    void list(MethodCallback<List<Manager>> callback);


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/save")
     void save(@ModelAttribute Manager manager, MethodCallback<Manager> callback);
}
