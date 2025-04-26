package com.ihminq.movie_hub.domain.usecase.movie;

import com.ihminq.movie_hub.domain.model.movie.MovieRespond;
import com.ihminq.movie_hub.domain.repository.movie.MovieRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class GetHomeMoviesUseCase {
    private final MovieRepository.Home mHomeMovieRepository;

    @Inject
    public GetHomeMoviesUseCase(MovieRepository.Home homeMovieRepository) {
        this.mHomeMovieRepository = homeMovieRepository;
    }

    public Single<MovieRespond> execute(String category) {
        return mHomeMovieRepository.getMoviesByCategory(category);
    }
}
