package com.ihminq.movie_hub.presentation.register.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ihminq.movie_hub.domain.exception.ValidationException;
import com.ihminq.movie_hub.domain.model.auth.User;
import com.ihminq.movie_hub.domain.usecase.auth.RegisterUseCase;
import com.ihminq.movie_hub.domain.utils.ValidateResult;
import com.ihminq.movie_hub.presentation.utils.SingleEvent;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class RegisterViewModel extends ViewModel {
    private static final String TAG = "RegisterViewModel";
    private final CompositeDisposable mDisposables = new CompositeDisposable();
    private final RegisterUseCase mRegisterUseCase;
    private final MutableLiveData<SingleEvent<ValidateResult>> mRegisterResultLive;

    @Inject
    public RegisterViewModel(RegisterUseCase registerUseCase) {
        this.mRegisterUseCase = registerUseCase;
        mRegisterResultLive = new MutableLiveData<>();
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
        mDisposables.add(disposable);
    }

    public LiveData<SingleEvent<ValidateResult>> getRegisterResultLive() {
        return this.mRegisterResultLive;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposables.clear();
    }
}
