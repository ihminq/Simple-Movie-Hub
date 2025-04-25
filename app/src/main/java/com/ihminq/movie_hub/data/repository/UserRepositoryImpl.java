package com.ihminq.movie_hub.data.repository;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ihminq.movie_hub.data.source.remote.firebase.UserDataSource;
import com.ihminq.movie_hub.domain.model.auth.User;
import com.ihminq.movie_hub.domain.repository.auth.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;

@Singleton
public class UserRepositoryImpl implements UserRepository {
    private static final String TAG = "UserRepositoryImpl";
    private final UserDataSource mUserDataSource;
    private final FirebaseAuth mFirebaseAuth;

    @Inject
    public UserRepositoryImpl(UserDataSource userDataSource, FirebaseAuth firebaseAuth) {
        this.mUserDataSource = userDataSource;
        this.mFirebaseAuth = firebaseAuth;
    }

    @Override
    public Completable register(User user) {
        return Single.<FirebaseUser>create(emitter ->
                        mFirebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                                .addOnSuccessListener(authResult -> {
                                    FirebaseUser firebaseUser = authResult.getUser();
                                    if (firebaseUser != null) {
                                        Log.d(TAG, "create user succeed");
                                        emitter.onSuccess(firebaseUser); // return FirebaseUser when register success
                                    }
                                    else {
                                        Log.d(TAG, "created user, null UID.");
                                        emitter.onError(new Exception("Firebase user is null."));
                                    }
                                })
                                .addOnFailureListener(e -> {
                                    Log.e(TAG, "Failed to create user", e);
                                    emitter.onError(e);
                                }) // return Completable.error if createUser with FirebaseAuth fail
                )
                // use flatMapCompletable to map Single<FirebaseUser> to Completable
                .flatMapCompletable(firebaseUser -> {
                    // get UID generated from FirebaseAuth set to User object
                    user.setUid(firebaseUser.getUid());
                    // save User to database
                    return mUserDataSource.saveNewUser(user);
                });
    }

    @Override
    public Completable login(User user) {
        return Completable.create(emitter ->
                mFirebaseAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                        .addOnSuccessListener(authResult -> {
                            FirebaseUser firebaseUser = authResult.getUser();
                            if (firebaseUser != null) {
                                Log.d(TAG, "Login success: " + firebaseUser.getUid());
                                emitter.onComplete();
                            }
                            else {
                                emitter.onError(new Exception("User is null"));
                            }
                        })
                        .addOnFailureListener(e -> {
                            Log.e(TAG, "Login failed", e);
                            emitter.onError(new Exception("Email or Password is incorrect."));
                        })
        );
    }

    @Override
    public Single<User> getUserById(String uid) {
        return mUserDataSource.getUserById(uid);
    }
}
