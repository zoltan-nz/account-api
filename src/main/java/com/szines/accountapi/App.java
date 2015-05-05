package com.szines.accountapi;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application<AppConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

        GuiceBundle<AppConfiguration> guiceBundle = GuiceBundle.<AppConfiguration>newBuilder()
                .addModule(new AppModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(AppConfiguration.class)
                .build();

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        LOGGER.info("Method App#run() called");
        for (int i = 0; i < configuration.getMessageRepetitions(); i++) {
            System.out.println( configuration.getMessage() );
        }
    }
}
