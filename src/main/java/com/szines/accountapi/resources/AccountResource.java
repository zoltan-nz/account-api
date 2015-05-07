package com.szines.accountapi.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.szines.accountapi.daos.AccountDAO;
import com.szines.accountapi.models.Account;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private final AccountDAO dao;

    public AccountResource(AccountDAO dao) {
        this.dao = dao;
    }

    @GET
    @Timed(name = "account-get-request")
    @Path("/{id}")
    @UnitOfWork
    public Optional<Account> getAccount(@PathParam("id") int id) {
        return dao.find(id);
    }

    @POST
    @Timed
    @UnitOfWork
    public Account create(Account account) {
        return dao.save(account);
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
