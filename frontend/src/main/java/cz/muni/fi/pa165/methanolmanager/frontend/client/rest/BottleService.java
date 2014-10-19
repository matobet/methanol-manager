package cz.muni.fi.pa165.methanolmanager.frontend.client.rest;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/bottles")
public interface BottleService {
    @GET
    RestAction<List<String>> getBottles();
}
