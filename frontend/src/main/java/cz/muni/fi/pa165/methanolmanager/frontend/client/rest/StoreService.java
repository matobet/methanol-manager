package cz.muni.fi.pa165.methanolmanager.frontend.client.rest;

import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreWithBottlesDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("/stores")
public interface StoreService extends RestService {
    @GET
    @Path("/{id}")
    void getStore(@PathParam("id") int id, MethodCallback<StoreDto> callback);

    @GET
    @Path("/{id}")
    void getStoreWithBottles(@PathParam("id") int id, MethodCallback<StoreWithBottlesDto> callback);

    @GET
    void getStores(MethodCallback<List<StoreDto>> callback);

    @POST
    @Options(expect = 201)
    void createStore(StoreDto store, MethodCallback<StoreDto> callback);

    @PUT
    @Path("/{id}")
    void updateStore(@PathParam("id") int id, StoreDto storeDto, MethodCallback<StoreDto> callback);

    @DELETE
    @Path("/{id}")
    void deleteStore(@PathParam("id") int id, MethodCallback<Void> callback);
}
