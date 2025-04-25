package com.ihminq.movie_hub.domain.usecase.auth;

import android.util.Log;

import com.ihminq.movie_hub.domain.exception.ValidationException;
import com.ihminq.movie_hub.domain.model.auth.User;
import com.ihminq.movie_hub.domain.repository.auth.UserRepository;
import com.ihminq.movie_hub.domain.utils.ValidateResult;
import com.ihminq.movie_hub.domain.utils.Validator;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class LoginUseCase {
    private static final String TAG = "LoginUseCase";
    private final UserRepository mUserRepository;

    @Inject
    public LoginUseCase(UserRepository userRepository) {
        this.mUserRepository = userRepository;
    }

    public Completable execute(User user) {
        // Validate email
        ValidateResult emailResult = Validator.isEmailValid(user.getEmail());
        if (!emailResult.isValid()) {
            return Completable.error(new ValidationException(emailResult));
        }

        // Validate password
        ValidateResult passwordResult = Validator.isPasswordValid(user.getPassword());
        if (!passwordResult.isValid()) {
            return Completable.error(new ValidationException(passwordResult));
        }

        Log.d(TAG, "User info valid");
        return mUserRepository.login(user)
                .subscribeOn(Schedulers.io());
    }
}
