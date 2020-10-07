package com.cinema.dao.impl;

import com.cinema.dao.MovieSessionDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.lib.Dao;
import com.cinema.model.MovieSession;
import com.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add movie session " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> allAvailableSessionsQuery =
                    criteriaBuilder.createQuery(MovieSession.class);
            Root<MovieSession> root = allAvailableSessionsQuery.from(MovieSession.class);
            root.fetch("movie");
            root.fetch("cinemaHall");
            Predicate time = criteriaBuilder.between(root.get("showTime"), date.atStartOfDay(),
                    date.atTime(LocalTime.MAX));
            Predicate movie = criteriaBuilder.equal(root.get("movie"), movieId);
            allAvailableSessionsQuery.select(root).where(criteriaBuilder.and(time, movie));
            return session.createQuery(allAvailableSessionsQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("there are no sessions for movie " + movieId
                    + " on the date of " + date, e);
        }
    }
}
