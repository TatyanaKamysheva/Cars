
package com.cars.client.rest;

import com.cars.shared.models.*;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/service")
public interface GWTService extends RestService {
    /*------------Managers------------------*/
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/managers")
    void listManager(MethodCallback<List<Manager>> methodCallback);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/managers")
    void saveManager(Manager manager, MethodCallback<Void> methodCallback);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/managers/{id}")
    void deleteManager(@PathParam("id") Long id, MethodCallback<Void> methodCallback);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/managers/update")
    void updateManager(Manager manager, MethodCallback<Void> methodCallback);

    /*------------Attributes------------------*/
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/attributes")
    void listAttributes(MethodCallback<List<Attribute>> methodCallback);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/attributes")
    void saveAttribute(Attribute attribute, MethodCallback<Void> methodCallback);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/attributes/{id}")
    void deleteAttribute(@PathParam("id") Long id, MethodCallback<Void> methodCallback);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/attributes/update")
    void updateAttribute(Attribute attribute, MethodCallback<Void> methodCallback);

    /*------------Customers------------------*/
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers")
    void listCustomer(MethodCallback<List<Customer>> methodCallback);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers")
    void saveCustomer(Customer customer, MethodCallback<Void> methodCallback);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers/{id}")
    void deleteCustomer(@PathParam("id") Long id, MethodCallback<Void> methodCallback);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers/update")
    void updateCustomer(Customer customer, MethodCallback<Void> methodCallback);

    /*------------Automobiles------------------*/
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/autos")
    void listAuto(MethodCallback<List<Automobile>> methodCallback);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/autos")
    void saveAuto(Automobile automobile, MethodCallback<Void> methodCallback);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/autos/{id}")
    void deleteAuto(@PathParam("id") Long id, MethodCallback<Void> methodCallback);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/autos/update")
    void updateAuto(Automobile automobile, MethodCallback<Void> methodCallback);

    /*------------Equipment------------------*/
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/equipment")
    void listEquip(MethodCallback<List<Equipment>> methodCallback);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/equipment")
    void saveEquip(Equipment equipment, MethodCallback<Void> methodCallback);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/equipment/{id}")
    void deleteEquip(@PathParam("id") Long id, MethodCallback<Void> methodCallback);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/equipment/update")
    void updateEquip(Equipment equipment, MethodCallback<Void> methodCallback);

    /*------------Purchase------------------*/
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/purchases")
    void listPurchase(MethodCallback<List<Purchase>> methodCallback);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/purchases")
    void savePurchase(Purchase purchase, MethodCallback<Void> methodCallback);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/purchases/{id}")
    void deletePurchase(@PathParam("id") Long id, MethodCallback<Void> methodCallback);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/purchases/update")
    void updatePurchase(Purchase purchase, MethodCallback<Void> methodCallback);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login_{login}_{password}")
    void loginUser(@PathParam("login") String login, @PathParam("password") String password, MethodCallback<UserLoginInfo> callback);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/equipment/popup_{id}_{modification}")
    void listAutoPopup(@PathParam("id") Long id, @PathParam("modification") String modification, MethodCallback<List<AutoPopup>> methodCallback);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/equipment_{id}")
    void listModifications(@PathParam("id") Long id, MethodCallback<List<String>> callback);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/managers/get_{id}")
    void getManager(@PathParam("id") Long id, MethodCallback<Manager> callback);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/get_{login}")
    void getUserByLogin(@PathParam("login") String login, MethodCallback<User> callback);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/update")
    void updateUser(User user, MethodCallback<Void> methodCallback);

}
