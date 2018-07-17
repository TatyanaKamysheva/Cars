
package com.cars.client.rest;

import com.cars.shared.models.Manager;
import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.RestServiceProxy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/service")
public interface GWTService extends RestService {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/manager/list")
    void listManager(MethodCallback<List<Manager>> methodCallback);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/manager/save")
    void saveManager(Manager manager, MethodCallback<Void> methodCallback);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/manager/delete")
    void deleteManager(Integer id, MethodCallback<Void> methodCallback);

    class Util {
        private static GWTService service;

        public static GWTService getService() {
            if (service == null) {
                service = GWT.create(GWTService.class);
            }
            Resource resource = new Resource(GWT.getModuleBaseURL() + "service");
            ((RestServiceProxy) service).setResource(resource);
            return service;
        }
    }
}
