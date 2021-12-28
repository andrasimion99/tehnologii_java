package com.example.documents.caller;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 */
@Path("/client")
@Singleton
public class HelloController {
    @Inject
    @RestClient
    private Service service;

    @GET
    public String sayHello() {
        return "Hello World";
    }

    @GET
    @Path("/documents")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Document> onClientSide() {
        return service.getDocuments();
    }
}
