package com.ihminq.movie_hub.data.source.remote.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ihminq.movie_hub.constant.FirebaseConstants;
import com.ihminq.movie_hub.domain.model.auth.User;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;

@Singleton
public class UserDataSource {
    private static final String TAG = "UserDataSource";
    private final DatabaseReference mUserRef;

    @Inject
    public UserDataSource(FirebaseDatabase firebaseDatabase) {
        this.mUserRef = firebaseDatabase.getReference(FirebaseConstants.USERS_REF);
    }

    public Completable saveNewUser(User user) {
        if (user.getUid() == null || user.getUid().isEmpty()) {
            Log.d(TAG, "UID null.");
            return Completable.error(new IllegalArgumentException("User UID cannot be null or empty when saving to database."));
        }
        return Completable.create(emitter -> {
            mUserRef.child(user.getUid()) // use generated UID by FirebaseAuth
                    .setValue(user) // save User to database
                    .addOnSuccessListener(unused -> {
                        Log.d(TAG, "Save new user succeed");
                        emitter.onComplete();
                    })
                    .addOnFailureListener(e -> {
                        Log.e(TAG, "Failed to save user: " + user.getUid(), e);
                        emitter.onError(e);
                    });
        });
    }

    public Single<User> getUserById(String uid) {
        //TODO: Change to ValueEventListener for ViewModel to notice changes in User
        return Single.create(emitter -> mUserRef.child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        if (user != null) {
                            Log.d(TAG, "Fetched user: " + user.getUid());
                            emitter.onSuccess(user);
                        }
                        else {
                            Log.d(TAG, "No user found for UID: " + uid);
                            emitter.onError(new Exception());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "Error fetching user: " + uid, error.toException());
                        emitter.onError(error.toException());
                    }
                }));
    }
}
