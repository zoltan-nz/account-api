package com.szines.accountapi.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.szines.accountapi.daos.AccountDao;
import com.szines.accountapi.models.Account;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private final AccountDao accountDao;

    @Inject
    public AccountResource(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Account> index() {
        return accountDao.all();
    }

    @GET
    @Timed(name = "account-get-request")
    @Path("/{id}")
    @UnitOfWork
    public Account getAccount(@PathParam("id") int id) {
        return accountDao.find(id);
    }

    @POST
    @Timed
    @UnitOfWork
    public Account create(Account account) {
        return accountDao.create(account);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAccount(@PathParam("id") int id) {
        return Response.noContent().build();
    }
}
