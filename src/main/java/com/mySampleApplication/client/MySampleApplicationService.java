package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.mySampleApplication.shared.Manager;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.RestServiceProxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/service")
public interface MySampleApplicationService extends RestService {
    java.util.logging.Logger logger = java.util.logging.Logger.getLogger("Logger");

    class Util {
        private static MySampleApplicationService instance;

        public static MySampleApplicationService getService() {
            if (instance == null) {
                instance = GWT.create(MySampleApplicationService.class);
            }
            Resource resource = new Resource(GWT.getModuleBaseURL() + "service");
            ((RestServiceProxy) instance).setResource(resource);
            return instance;
        }
    }

    @GET
    @Path("/loadInfo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void getInfo(MethodCallback<Manager> callback);

    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void list(MethodCallback<List<Manager>> callback);
}
