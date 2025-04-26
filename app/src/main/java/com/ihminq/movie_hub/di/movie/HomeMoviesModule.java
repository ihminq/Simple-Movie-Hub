package com.ihminq.movie_hub.di.movie;

import com.ihminq.movie_hub.data.repository.HomeMovieRepositoryImpl;
import com.ihminq.movie_hub.domain.repository.movie.MovieRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class HomeMoviesModule {

    @Binds
    @Singleton
    public abstract MovieRepository.Home bindHomeMovieRepository(HomeMovieRepositoryImpl homeMovieRepositoryImpl);
}
