package com.ihminq.movie_hub.di.auth;

import com.ihminq.movie_hub.data.repository.UserRepositoryImpl;
import com.ihminq.movie_hub.domain.repository.auth.UserRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class UserModule {
    @Binds
    @Singleton
    public abstract UserRepository bindUserRepository(UserRepositoryImpl userRepositoryImpl);
}
