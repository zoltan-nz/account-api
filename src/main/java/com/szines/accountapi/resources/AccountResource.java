package com.szines.accountapi.resources;

import com.codahale.metrics.annotation.Timed;
import com.szines.accountapi.api.Account;

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
        return Response.ok(new Account(id, "Example Name", 365.89)).build();
    }

    @POST
    public Response createAccount(Account account) {
        return Response.created(null).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAccount(@PathParam("id") int id) {
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAccount(@PathParam("id") int id, Account account) {
        return Response.ok(new Account(id, account.getName(), account.getBalance())).build();
    }
}
