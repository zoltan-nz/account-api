package com.szines.accountapi;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.szines.accountapi.models.Account;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfiguration> {

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

        bootstrap.addBundle(migrationsBundle);
        bootstrap.addBundle(hibernateBundle);

        AppModule appModule = new AppModule(hibernateBundle);

        GuiceBundle<AppConfiguration> guiceBundle = GuiceBundle.<AppConfiguration>newBuilder()
                .addModule(appModule)
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(AppConfiguration.class)
                .build();

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        environment.getObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        environment.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }
}
