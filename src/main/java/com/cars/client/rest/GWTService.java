package com.cars.client.rest;

import com.cars.shared.models.AttributePopup;
import com.cars.shared.models.Modification;
import com.cars.shared.models.Response;
import com.cars.shared.models.UserLoginInfo;
import com.cars.shared.models.entities.*;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/service")
public interface GWTService extends RestService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers")
    void saveCustomer(Customer customer, MethodCallback<Response> methodCallback);

    /*------------Managers------------------*/
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/managers")
    void listManager(MethodCallback<List<Employee>> methodCallback);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/managers")
    void saveManager(Employee manager, MethodCallback<Response> methodCallback);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/managers/{id}")
    void deleteManager(@PathParam("id") Long id, MethodCallback<Response> methodCallback);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/managers/update")
    void updateManager(Employee manager, MethodCallback<Response> methodCallback);

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
    void saveAttribute(Attribute attribute, MethodCallback<Response> methodCallback);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/attributes/{id}")
    void deleteAttribute(@PathParam("id") Long id, MethodCallback<Response> methodCallback);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/attributes/update")
    void updateAttribute(Attribute attribute, MethodCallback<Response> methodCallback);

    /*------------Customers------------------*/
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers")
    void listCustomer(MethodCallback<List<Customer>> methodCallback);



    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers/{id}")
    void deleteCustomer(@PathParam("id") Long id, MethodCallback<Response> methodCallback);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers/update")
    void updateCustomer(Customer customer, MethodCallback<Response> methodCallback);

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
    void saveAuto(Automobile automobile, MethodCallback<Response> methodCallback);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/autos/{id}")
    void deleteAuto(@PathParam("id") Long id, MethodCallback<Response> methodCallback);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/autos/update")
    void updateAuto(Automobile automobile, MethodCallback<Response> methodCallback);

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
    void saveEquip(Equipment equipment, MethodCallback<Response> methodCallback);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/equipment/{id}")
    void deleteEquip(@PathParam("id") Long id, MethodCallback<Response> methodCallback);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/equipment/update")
    void updateEquip(Equipment equipment, MethodCallback<Response> methodCallback);

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
    void savePurchase(Purchase purchase, MethodCallback<Response> methodCallback);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/purchases/{id}")
    void deletePurchase(@PathParam("id") Long id, MethodCallback<Response> methodCallback);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/purchases/update")
    void updatePurchase(Purchase purchase, MethodCallback<Response> methodCallback);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login_{login}_{password}")
    void loginUser(@PathParam("login") String login, @PathParam("password") String password, MethodCallback<UserLoginInfo> callback);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/equipment/popup_{id}_{modification}")
    void listAutoPopup(@PathParam("id") Long id, @PathParam("modification") String modification, MethodCallback<List<Equipment>> methodCallback);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/equipment_{id}")
    void listModifications(@PathParam("id") Long id, MethodCallback<List<Modification>> callback);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/managers/get_{id}")
    void getManager(@PathParam("id") Long id, MethodCallback<Employee> callback);

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

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}")
    void deleteUser(@PathParam("id") Long id, MethodCallback<Response> methodCallback);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/equipment/filter/{id}")
    void filterEquipment(@PathParam("id") Long id, MethodCallback<List<Equipment>> methodCallback);
}
