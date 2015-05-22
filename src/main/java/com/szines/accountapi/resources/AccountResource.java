package com.szines.accountapi.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.szines.accountapi.daos.account.AccountStore;
import com.szines.accountapi.models.Account;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private final AccountStore accountStore;

    @Inject
    public AccountResource(AccountStore accountStore) {
        this.accountStore = accountStore;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Account> index() {
        return accountStore.all();
    }

    @GET
    @Timed(name = "account-get-request")
    @Path("/{number}")
    @UnitOfWork
    public Account getAccount(@PathParam("number") String number) {
        return accountStore.findByNumber(number);
    }

    @GET
    @Timed
    @Path("/")
    @UnitOfWork
    public List<Account> all() {
        return accountStore.all();
    }

    @GET
    @Timed
    @Path("/search")
    @UnitOfWork
    public Account search(@QueryParam("id") int id, @QueryParam("number") String number) {
        if (number != null) {
            return accountStore.findByNumber(number);
        }

        return accountStore.findById(id);
    }

    @POST
    @Timed
    @UnitOfWork
    public Account create(Account account) {
        return accountStore.create(account);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAccount(@PathParam("id") int id) {
        return Response.noContent().build();
    }
}
