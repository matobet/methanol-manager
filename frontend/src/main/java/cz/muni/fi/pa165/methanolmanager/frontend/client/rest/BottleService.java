package cz.muni.fi.pa165.methanolmanager.frontend.client.rest;

import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import org.fusesource.restygwt.client.Options;

@Path("/bottles")
public interface BottleService extends RestService {
    @GET
    @Path("/{id}")
    void getBottle(@PathParam("id") int id, MethodCallback<BottleDto> callback);
    
    @GET
    void getBottles(MethodCallback<List<BottleDto>> callback);

    @POST
    @Options(expect = 201)
    void createBottle(BottleDto bottle, MethodCallback<BottleDto> callback);

    @PUT
    @Path("/{id}")
    void updateBottle(@PathParam("id") int id, BottleDto bottleDto, MethodCallback<BottleDto> callback);

    @DELETE
    @Path("/{id}")
    void deleteBottle(@PathParam("id") int id, MethodCallback<Void> callback);
}