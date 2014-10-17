package cz.muni.fi.pa165.methanolmanager.frontend.client.rest;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/stores")
public interface StoreService extends RestService {
    @GET
    void getStores(MethodCallback<List<String>> callback);
}
