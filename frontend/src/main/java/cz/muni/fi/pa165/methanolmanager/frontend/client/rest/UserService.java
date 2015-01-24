package cz.muni.fi.pa165.methanolmanager.frontend.client.rest;

import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by zuzana on 1/24/2015.
 */
@Path("/users")
public interface UserService extends RestService {
    @GET
    @Path("/{id}")
    void getUser(@PathParam("id") int id, MethodCallback<UserDto> callback);

    @GET
    void getUsers(MethodCallback<List<UserDto>> callback);

    @POST
    @Options(expect = 201)
    void createUser(UserDto user, MethodCallback<UserDto> callback);

    @PUT
    @Path("/{id}")
    void updateUser(@PathParam("id") int id, UserDto userDto, MethodCallback<UserDto> callback);

    @DELETE
    @Path("/{id}")
    void deleteUser(@PathParam("id") int id, MethodCallback<Void> callback);
}
