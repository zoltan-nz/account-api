package com.szines.accountapi.daos.account;

import com.szines.accountapi.models.Account;
import org.hibernate.HibernateException;

import java.util.List;

public interface AccountDAO {

    Account find(int id) throws HibernateException;

    List<Account> all() throws HibernateException;

    Account create(Account entity) throws HibernateException;

    void delete(Account entity) throws HibernateException;
}
