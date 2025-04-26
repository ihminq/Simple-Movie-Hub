package com.ihminq.movie_hub.data.repository;

import com.ihminq.movie_hub.constant.APIConstants;
import com.ihminq.movie_hub.data.source.remote.retrofit.MovieService;
import com.ihminq.movie_hub.domain.model.movie.MovieRespond;
import com.ihminq.movie_hub.domain.repository.movie.MovieRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class HomeMovieRepositoryImpl implements MovieRepository.Home {
    private final MovieService mMovieService;

    @Inject
    public HomeMovieRepositoryImpl(MovieService movieService) {
        this.mMovieService = movieService;
    }

    @Override
    public Single<MovieRespond> getMoviesByCategory(String category) {
        return mMovieService.getHomeMovieByCategory(category, APIConstants.API_KEY);
    }
}
