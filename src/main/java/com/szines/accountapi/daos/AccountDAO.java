package com.szines.accountapi.daos;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.szines.accountapi.models.Account;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.util.List;

@Singleton
public class AccountDAO extends AbstractDAO<Account> {

    @Inject
    public AccountDAO(SessionFactory provider) {
        super(provider);
    }

    public Account find(int id) {
        return get(id);
    }

    public List<Account> all() {
        return (List<Account>) criteria().list();
    }

    public Account save(Account entity) throws HibernateException {
        return persist(entity);
    }

    public void delete(Account entity) throws HibernateException {
        currentSession().delete(entity);
    }
}

