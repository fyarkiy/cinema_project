package com.cinema.service;

import com.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {
    MovieSession add(MovieSession entity);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
