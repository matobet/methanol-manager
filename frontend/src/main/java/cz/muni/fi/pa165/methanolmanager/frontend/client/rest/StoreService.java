package cz.muni.fi.pa165.methanolmanager.frontend.client.rest;

import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/stores")
public interface StoreService extends RestService {
    @GET
    @Path("/{id}")
    void getStore(@PathParam("id") int id, MethodCallback<StoreDto> callback);

    @GET
    void getStores(MethodCallback<List<StoreDto>> callback);

    @DELETE
    @Path("/{id}")
    void deleteStore(@PathParam("id") int id, MethodCallback<Void> callback);
}
