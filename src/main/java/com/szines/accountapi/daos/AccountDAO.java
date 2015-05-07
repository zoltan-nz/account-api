package com.szines.accountapi.daos;


import com.google.common.base.Optional;
import com.szines.accountapi.models.Account;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * A DAO for managing {@link Account} objects.
 */
public class AccountDAO extends AbstractDAO<Account> {

    /**
     * Creates a new DAO with the given session provider.
     *
     * @param provider a session provider
     */
    public AccountDAO(SessionFactory provider) {
        super(provider);
    }

    /**
     * Returns the {@link Account} with the given ID.
     *
     * @param id the entity ID
     * @return the entity with the given ID
     */
    public Optional<Account> find(int id) {
        return Optional.fromNullable(get(id));
    }

    /**
     * Returns all {@link Account} entities.
     *
     * @return the list of entities
     */
    public List<Account> all() {
        return (List<Account>) criteria().list();
    }

    /**
     * Saves the given {@link Account}.
     *
     * @param entity the entity to save
     * @return the persistent entity
     */
    @UnitOfWork
    public Account save(Account entity) throws HibernateException {
        return persist(entity);
    }

    /**
     * Merges the given {@link Account}.
     *
     * @param entity the entity to merge
     * @return the persistent entity
     */
    public Account merge(Account entity) throws HibernateException {
        return (Account) currentSession().merge(entity);
    }

    /**
     * Deletes the given {@link Account}.
     *
     * @param entity the entity to delete
     */
    public void delete(Account entity) throws HibernateException {
        currentSession().delete(entity);
    }
}

