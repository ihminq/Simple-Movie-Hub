package com.ihminq.movie_hub.domain.usecase.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CheckLoggedInStatusUseCase {
    private final FirebaseAuth mFirebaseAuth;

    @Inject
    public CheckLoggedInStatusUseCase(FirebaseAuth firebaseAuth) {
        this.mFirebaseAuth = firebaseAuth;
    }

    public boolean execute() {
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        return currentUser != null;
    }
}
