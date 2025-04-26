package com.ihminq.movie_hub.data.source.remote.retrofit;

import com.ihminq.movie_hub.domain.model.movie.MovieRespond;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
    @GET("movie/{category}?page=1")
    Single<MovieRespond> getHomeMovieByCategory(
            @Path("category") String category,
            @Query("api_key") String apiKey
    );
}
