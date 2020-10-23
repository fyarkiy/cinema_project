package com.cinema.service.impl.mapper;

import com.cinema.model.Movie;
import com.cinema.model.dto.MovieRequestDto;
import com.cinema.model.dto.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public Movie mapDtoToMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        return movie;
    }

    public MovieResponseDto mapMovieToDto(Movie movie) {
        return new MovieResponseDto(movie.getId(), movie.getTitle());
    }

}
