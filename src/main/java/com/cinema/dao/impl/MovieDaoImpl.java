package com.cinema.dao.impl;

import com.cinema.dao.MovieDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.lib.Dao;
import com.cinema.model.Movie;
import com.cinema.util.HibernateUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieDaoImpl implements MovieDao {
    private static final Logger logger = Logger.getLogger(MovieDaoImpl.class);

    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            logger.info("Movie" + movie + " was created");
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Not able to add movie to data base", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Movie> getAllQueryMovie = session.createQuery("from Movie ", Movie.class);
            return getAllQueryMovie.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Not able to retrieve all movies from database", e);
        }
    }
}
