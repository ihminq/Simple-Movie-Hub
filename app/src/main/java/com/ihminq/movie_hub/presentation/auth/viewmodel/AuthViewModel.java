package com.ihminq.movie_hub.presentation.auth.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ihminq.movie_hub.domain.exception.ValidationException;
import com.ihminq.movie_hub.domain.model.auth.User;
import com.ihminq.movie_hub.domain.usecase.auth.CheckLoggedInStatusUseCase;
import com.ihminq.movie_hub.domain.usecase.auth.LoginUseCase;
import com.ihminq.movie_hub.domain.usecase.auth.LogoutUseCase;
import com.ihminq.movie_hub.domain.usecase.auth.RegisterUseCase;
import com.ihminq.movie_hub.domain.utils.ValidateResult;
import com.ihminq.movie_hub.presentation.utils.SingleEvent;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private final LoginUseCase mLoginUseCase;
    private final CheckLoggedInStatusUseCase mCheckLoggedInStatusUseCase;
    private final RegisterUseCase mRegisterUseCase;
    private final LogoutUseCase mLogoutUseCase;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private final MutableLiveData<SingleEvent<ValidateResult>> mLoginResultLive = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mLoggedOutStatusLive = new MutableLiveData<>();
    private final MutableLiveData<SingleEvent<ValidateResult>> mRegisterResultLive = new MutableLiveData<>();

    @Inject
    public AuthViewModel(LoginUseCase loginUseCase, CheckLoggedInStatusUseCase checkLoggedInStatusUseCase, RegisterUseCase registerUseCase, LogoutUseCase logoutUseCase) {
        this.mLoginUseCase = loginUseCase;
        this.mCheckLoggedInStatusUseCase = checkLoggedInStatusUseCase;
        this.mRegisterUseCase = registerUseCase;
        this.mLogoutUseCase = logoutUseCase;
    }

    public void login(User user) {
        Log.d(TAG, "User to Login: " + user);
        Disposable disposable = mLoginUseCase.execute(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> mLoginResultLive.postValue(new SingleEvent<>(new ValidateResult(true, "Login successful"))),
                        err -> {
                            ValidateResult vr = err instanceof ValidationException
                                    ? ((ValidationException)err).getResult()
                                    : new ValidateResult(false, err.getMessage(), ValidateResult.Field.NONE);
                            mLoginResultLive.postValue(new SingleEvent<>(vr));
                        }
                );
        mDisposable.add(disposable);
    }

    public void logout() {
        mDisposable.add(
                mLogoutUseCase.logout()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    Log.d(TAG, "Logout success");
                                    mLoggedOutStatusLive.setValue(true);
                                },
                                err -> {
                                    Log.e(TAG, "Logout failed", err);
                                    mLoggedOutStatusLive.setValue(false);
                                }
                        )
        );
    }

    public void register(User user) {
        Log.d(TAG, "User to register: " + user);
        Disposable disposable = mRegisterUseCase.execute(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> mRegisterResultLive.postValue(new SingleEvent<>(new ValidateResult(true, "Registration successful"))),
                        err -> {
                            ValidateResult vr = err instanceof ValidationException
                                    ? ((ValidationException)err).getResult()
                                    : new ValidateResult(false, err.getMessage(), ValidateResult.Field.NONE);
                            mRegisterResultLive.postValue(new SingleEvent<>(vr));
                        }
                );
        mDisposable.add(disposable);
    }

    public LiveData<SingleEvent<ValidateResult>> getRegisterResultLive() {
        return this.mRegisterResultLive;
    }

    public LiveData<SingleEvent<ValidateResult>> getLoginResultLive() {
        return this.mLoginResultLive;
    }

    public LiveData<Boolean> getLoggedOutStatusLive() {
        return this.mLoggedOutStatusLive;
    }

    public boolean isCurrentUserLoggedIn() {
        return mCheckLoggedInStatusUseCase.execute();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }
}
