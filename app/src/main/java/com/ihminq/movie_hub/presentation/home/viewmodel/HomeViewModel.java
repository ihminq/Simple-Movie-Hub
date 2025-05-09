package com.ihminq.movie_hub.presentation.home.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ihminq.movie_hub.domain.model.auth.User;
import com.ihminq.movie_hub.domain.usecase.auth.LogoutUseCase;
import com.ihminq.movie_hub.domain.usecase.home.GetCurrentUserUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private static final String TAG = "HomeViewModel";
    private final MutableLiveData<User> mCurrentUserLive = new MutableLiveData<>();
    private final GetCurrentUserUseCase mGetCurrentUserUseCase;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    public HomeViewModel(GetCurrentUserUseCase getCurrentUserUseCase) {
        Log.d(TAG, "onCreate");
        this.mGetCurrentUserUseCase = getCurrentUserUseCase;
    }

    public void loadCurrentUser() {
        mDisposable.add(
                mGetCurrentUserUseCase.execute()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                user -> {
                                    Log.d(TAG, "GetCurrentUser success: " + user);
                                    mCurrentUserLive.setValue(user);
                                },
                                err -> {
                                    Log.e(TAG, "GetCurrentUser failed", err);
                                    mCurrentUserLive.setValue(null);
                                }
                        )
        );
    }

    public LiveData<User> getCurrentUserLive() {
        return this.mCurrentUserLive;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }
}
