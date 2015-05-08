package com.szines.accountapi.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.szines.accountapi.daos.AccountDAO;
import com.szines.accountapi.models.Account;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private final AccountDAO accountDAO;

    @Inject
    public AccountResource(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Account> index() {
        return accountDAO.all();
    }

    @GET
    @Timed(name = "account-get-request")
    @Path("/{id}")
    @UnitOfWork
    public Account getAccount(@PathParam("id") int id) {
        return accountDAO.find(id);
    }

    @POST
    @Timed
    @UnitOfWork
    public Account create(Account account) {
        return accountDAO.save(account);
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
