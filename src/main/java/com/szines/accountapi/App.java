package com.szines.accountapi;

import com.szines.accountapi.daos.AccountDAO;
import com.szines.accountapi.models.Account;
import com.szines.accountapi.resources.AccountResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryHealthCheck;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfiguration> {

    AppModule appModule;

    private final MigrationsBundle<AppConfiguration> migrationsBundle = new MigrationsBundle<AppConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private final HibernateBundle<AppConfiguration> hibernateBundle = new HibernateBundle<AppConfiguration>(Account.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

        appModule = new AppModule();

//        GuiceBundle<AppConfiguration> guiceBundle = GuiceBundle.<AppConfiguration>newBuilder()
//                .addModule(appModule)
//                .enableAutoConfig(getClass().getPackage().getName())
//                .setConfigClass(AppConfiguration.class)
//                .build();

//        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(migrationsBundle);
        bootstrap.addBundle(hibernateBundle);

    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        final AccountDAO accountDAO = new AccountDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new AccountResource(accountDAO));
        environment.healthChecks().register("database", new SessionFactoryHealthCheck(hibernateBundle.getSessionFactory(), configuration.getDataSourceFactory().getValidationQuery()));

    }


}
