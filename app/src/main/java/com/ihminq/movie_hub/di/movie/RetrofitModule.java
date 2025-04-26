package com.ihminq.movie_hub.di.movie;

import com.ihminq.movie_hub.data.source.remote.retrofit.MovieService;
import com.ihminq.movie_hub.data.source.remote.retrofit.RetrofitClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {

    @Singleton
    @Provides
    public RetrofitClient provideRetrofitClient() {
        return new RetrofitClient();
    }

    @Provides
    public MovieService provideMovieService(RetrofitClient retrofitClient) {
        return retrofitClient.getMovieService();
    }
}
