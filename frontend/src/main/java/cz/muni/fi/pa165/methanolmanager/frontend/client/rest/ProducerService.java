package cz.muni.fi.pa165.methanolmanager.frontend.client.rest;

import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import org.fusesource.restygwt.client.Attribute;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("/producers")
public interface ProducerService extends RestService {
    @GET
    @Path("/{id}")
    void getProducer(@PathParam("id") int id, MethodCallback<ProducerDto> callback);

    @GET
    void getProducers(MethodCallback<List<ProducerDto>> callback);

    @POST
    @Options(expect = 201)
    void createProducer(ProducerDto store, MethodCallback<ProducerDto> callback);

    @PUT
    @Path("/{id}")
    void updateProducer(@PathParam("id") @Attribute("id") ProducerDto storeDto, MethodCallback<ProducerDto> callback);

    @DELETE
    @Path("/{id}")
    void deleteProducer(@PathParam("id") int id, MethodCallback<Void> callback);
}
