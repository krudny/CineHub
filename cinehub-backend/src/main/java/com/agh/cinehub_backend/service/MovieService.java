package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.DTO.MovieRequest;
import com.agh.cinehub_backend.model.Genre;
import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreService genreService;

    public void addMovie(MovieRequest request) {
        Genre genre = genreService.findByName(request.getGenre());

        Movie newFilm = Movie.builder()
                .description(request.getDescription())
                .title(request.getTitle())
                .director(request.getDirector())
                .duration(request.getDuration())
                .publishDate(request.getPublishDate())
                .production(request.getProduction())
                .genre(genre)
                .build();


        // TODO: any error handling? empty data inserts into table
        movieRepository.save(newFilm);
    }
}
