package com.szines.accountapi.daos.account;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.szines.accountapi.models.Account;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

@Singleton
public class AccountStore extends AbstractDAO<Account> implements AccountDAO {

    @Inject
    public AccountStore(SessionFactory provider) {
        super(provider);
    }

    @Override
    public Account findById(int id) throws HibernateException {
        return get(id);
    }

    @Override
    public Account findByNumber(String number) throws HibernateException {
        Criteria criteria = criteria().add(Restrictions.eq("number", number));
        return uniqueResult(criteria);
    }

    @Override
    public List<Account> all() {
        return (List<Account>) criteria().list();
    }

    @Override
    public Account create(Account entity) throws HibernateException {
        Account exist = findByNumber(entity.getNumber());

        if (exist != null) return exist;

        return persist(entity);
    }

    @Override
    public void delete(Account entity) throws HibernateException {
        currentSession().delete(entity);
    }
}

