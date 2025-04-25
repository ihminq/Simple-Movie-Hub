package com.ihminq.movie_hub.domain.usecase.home;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ihminq.movie_hub.domain.model.auth.User;
import com.ihminq.movie_hub.domain.repository.auth.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class GetCurrentUserUseCase {
    private static final String TAG = "GetCurrentUserUseCase";
    private final UserRepository mUserRepository;
    private final FirebaseAuth mFirebaseAuth;

    @Inject
    public GetCurrentUserUseCase(UserRepository userRepository, FirebaseAuth firebaseAuth) {
        this.mUserRepository = userRepository;
        this.mFirebaseAuth = firebaseAuth;
    }

    public Single<User> execute() {
        return Single.defer(() -> {
            // get currentUser from FirebaseAuth
            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

            // check firebaseUser is null or UID is empty
            if (firebaseUser == null || firebaseUser.getUid().isEmpty()) {
                Log.d(TAG, "Cannot get current user info from Firebase Auth");
                return Single.error(new Exception());
            }

            return mUserRepository.getUserById(firebaseUser.getUid());
        });
    }
}
