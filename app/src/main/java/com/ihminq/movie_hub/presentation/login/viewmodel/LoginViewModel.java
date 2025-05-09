package com.ihminq.movie_hub.presentation.login.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ihminq.movie_hub.domain.exception.ValidationException;
import com.ihminq.movie_hub.domain.model.auth.User;
import com.ihminq.movie_hub.domain.usecase.auth.CheckLoggedInStatusUseCase;
import com.ihminq.movie_hub.domain.usecase.auth.LoginUseCase;
import com.ihminq.movie_hub.domain.utils.ValidateResult;
import com.ihminq.movie_hub.presentation.utils.SingleEvent;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";
    private final LoginUseCase mLoginUseCase;
    private final CheckLoggedInStatusUseCase mCheckLoggedInStatusUseCase;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private final MutableLiveData<SingleEvent<ValidateResult>> mLoginResultLive;

    @Inject
    public LoginViewModel(LoginUseCase loginUseCase, CheckLoggedInStatusUseCase checkLoggedInStatusUseCase) {
        this.mLoginUseCase = loginUseCase;
        this.mCheckLoggedInStatusUseCase = checkLoggedInStatusUseCase;
        mLoginResultLive = new MutableLiveData<>();
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

    public LiveData<SingleEvent<ValidateResult>> getLoginResultLive() {
        return this.mLoginResultLive;
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
