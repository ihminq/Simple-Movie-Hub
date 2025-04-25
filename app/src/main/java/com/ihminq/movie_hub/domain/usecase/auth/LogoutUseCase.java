package com.ihminq.movie_hub.domain.usecase.auth;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;

@Singleton
public class LogoutUseCase {
    private static final String TAG = "LogoutUseCase";
    private final FirebaseAuth mFirebaseAuth;

    @Inject
    public LogoutUseCase(FirebaseAuth firebaseAuth) {
        this.mFirebaseAuth = firebaseAuth;
    }

    public Completable logout() {
        return Completable.create(emitter -> {
            FirebaseUser user = mFirebaseAuth.getCurrentUser();
            if (user != null) {
                Log.d(TAG, "Logging out user: " + user.getUid());
                mFirebaseAuth.signOut();
                emitter.onComplete();
            }
            else {
                Log.d(TAG, "No user logged in");
                emitter.onError(new IllegalStateException("No user is currently logged in"));
            }
        });
    }
}
