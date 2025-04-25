package com.ihminq.movie_hub.domain.repository.auth;

import com.ihminq.movie_hub.domain.model.auth.User;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface UserRepository {
    Completable register(User user);
    Completable login(User user);
    Single<User> getUserById(String uid);
}
