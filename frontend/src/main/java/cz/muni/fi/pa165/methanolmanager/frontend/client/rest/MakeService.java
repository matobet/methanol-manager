package cz.muni.fi.pa165.methanolmanager.frontend.client.rest;

import cz.muni.fi.pa165.methanolmanager.service.dto.MakeDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("/makes")
public interface MakeService extends RestService {
    @GET
    @Path("/{id}")
    void getMake(@PathParam("id") int id, MethodCallback<MakeDto> callback);
    
    @GET
    void getMakes(MethodCallback<List<MakeDto>> callback);

    @POST
    @Options(expect = 201)
    void createMake(MakeDto make, MethodCallback<MakeDto> callback);

    @PUT
    @Path("/{id}")
    void updateMake(@PathParam("id") int id, MakeDto makeDto, MethodCallback<MakeDto> callback);

    @DELETE
    @Path("/{id}")
    void deleteMake(@PathParam("id") int id, MethodCallback<Void> callback);
}