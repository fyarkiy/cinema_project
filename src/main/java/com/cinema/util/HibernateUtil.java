package com.cinema.util;

import com.cinema.exception.NotAbleToSetSessionFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new NotAbleToSetSessionFactory("Error Creating session Factory", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
