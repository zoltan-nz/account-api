package com.szines.accountapi;

import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

public class AppModule extends AbstractModule {

    private final HibernateBundle hibernateBundle;

    public AppModule(HibernateBundle hibernateBundle) {
        Preconditions.checkNotNull(hibernateBundle);
        this.hibernateBundle = hibernateBundle;
    }

    @Override
    protected void configure() {
    }

    @Provides
    SessionFactory getSessionFactory() {
        return hibernateBundle.getSessionFactory();
    }
}
