package cz.muni.fi.pa165.methanolmanager.frontend.client.rest;

import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/bottles")
public interface BottleService extends RestService {
    @GET
    void getBottles(MethodCallback<List<String>> bottles);
}
