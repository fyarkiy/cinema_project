package com.cinema.dao.impl;

import com.cinema.dao.UserDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.lib.Dao;
import com.cinema.model.User;
import com.cinema.util.HibernateUtil;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoImpl implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            logger.info("User " + user + " was created.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("user" + user + "was not created", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> userQuery =
                    session.createQuery("from User u where u.email =: email", User.class);
            userQuery.setParameter("email", email);
            return userQuery.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Exception while getting user with email "
                    + email, e);
        }
    }
}
