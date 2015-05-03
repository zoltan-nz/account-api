package com.szines.accountapi;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AccountApiApplication extends Application<AccountApiConfiguration> {

    public static void main(String[] args) throws Exception {
        new AccountApiApplication().run(args);
    }

    public void initialize(Bootstrap<AccountApiConfiguration> bootstrap) {

    }

    @Override
    public void run(AccountApiConfiguration configuration, Environment environment) {

    }
}
