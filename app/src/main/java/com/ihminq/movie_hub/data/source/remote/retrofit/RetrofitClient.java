package com.ihminq.movie_hub.data.source.remote.retrofit;

import com.ihminq.movie_hub.constant.APIConstants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RetrofitClient {
    private final Retrofit mRetrofit;

    @Inject
    public RetrofitClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(APIConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public MovieService getMovieService() {
        return mRetrofit.create(MovieService.class);
    }
}
