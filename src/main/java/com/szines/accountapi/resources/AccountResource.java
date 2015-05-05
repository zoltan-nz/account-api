package com.szines.accountapi.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    @GET
    @Timed(name = "account-get-request")
    @Path("/{id}")
    public Response getAccount(@PathParam("id") int id) {
        return Response.ok("{account_id: " + id + ", name: 'Dummy Name' }").build();
    }

    @POST
    public Response createAccount(@FormParam("name") String name) {
        return Response.created(null).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAccount(@PathParam("id") int id) {
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAccount(@PathParam("id") int id, @FormParam("name") String name) {
        return Response.ok("{account_id:"+ id +", name:" + name + "}").build();
    }
}
