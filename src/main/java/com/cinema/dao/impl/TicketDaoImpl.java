package com.cinema.dao.impl;

import com.cinema.dao.TicketDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.lib.Dao;
import com.cinema.model.Ticket;
import com.cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    private static final Logger logger = Logger.getLogger(TicketDaoImpl.class);

    @Override
    public Ticket add(Ticket ticket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            logger.info("Ticket " + ticket + " was created.");
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("can't create ticket " + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
