package com.ihminq.movie_hub.domain.repository.movie;

import com.ihminq.movie_hub.domain.model.movie.MovieRespond;

import io.reactivex.Single;

public interface MovieRepository {
    interface Home {
        Single<MovieRespond> getMoviesByCategory(String category);
    }
}
