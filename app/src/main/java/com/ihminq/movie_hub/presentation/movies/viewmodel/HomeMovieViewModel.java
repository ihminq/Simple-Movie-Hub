package com.ihminq.movie_hub.presentation.movies.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ihminq.movie_hub.constant.APIConstants;
import com.ihminq.movie_hub.domain.model.movie.MovieHome;
import com.ihminq.movie_hub.domain.usecase.movie.GetHomeMoviesUseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class HomeMovieViewModel extends ViewModel {
    private static final String TAG = "HomeMovieViewModel";
    private final GetHomeMoviesUseCase mGetHomeMoviesUseCase;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private final MutableLiveData<ArrayList<MovieHome>> mPopularMoviesLive = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<MovieHome>> mTopRatedMoviesLive = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<MovieHome>> mUpcomingMoviesLive = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<MovieHome>> mNowPlayingMoviesLive = new MutableLiveData<>();

    @Inject
    public HomeMovieViewModel(GetHomeMoviesUseCase getHomeMoviesUseCase) {
        this.mGetHomeMoviesUseCase = getHomeMoviesUseCase;
        loadHomeMovies();
    }

    public void loadHomeMovies() {
        mDisposable.add(mGetHomeMoviesUseCase.execute(APIConstants.PATH_POPULAR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> mPopularMoviesLive.setValue(response.getResults()),
                        throwable -> {
                            mPopularMoviesLive.setValue(null);
                            Log.d(TAG, "Error when loading popular movie: " + throwable.getMessage());
                        }
                ));

        mDisposable.add(mGetHomeMoviesUseCase.execute(APIConstants.PATH_TOPRATED)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> mTopRatedMoviesLive.setValue(response.getResults()),
                        throwable -> {
                            mTopRatedMoviesLive.setValue(null);
                            Log.d(TAG, "Error when loading toprated movie: " + throwable.getMessage());
                        }
                ));

        mDisposable.add(mGetHomeMoviesUseCase.execute(APIConstants.PATH_UPCOMING)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> mUpcomingMoviesLive.setValue(response.getResults()),
                        throwable -> {
                            mUpcomingMoviesLive.setValue(null);
                            Log.d(TAG, "Error when loading upcoming movie: " + throwable.getMessage());
                        }
                ));

        mDisposable.add(mGetHomeMoviesUseCase.execute(APIConstants.PATH_NOWPLAYING)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> mNowPlayingMoviesLive.setValue(response.getResults()),
                        throwable -> {
                            mNowPlayingMoviesLive.setValue(null);
                            Log.d(TAG, "Error when loading nowplaying movie: " + throwable.getMessage());
                        }
                ));
    }

    public MutableLiveData<ArrayList<MovieHome>> getPopularMoviesLive() {
        return mPopularMoviesLive;
    }

    public MutableLiveData<ArrayList<MovieHome>> getTopRatedMoviesLive() {
        return mTopRatedMoviesLive;
    }

    public MutableLiveData<ArrayList<MovieHome>> getUpcomingMoviesLive() {
        return mUpcomingMoviesLive;
    }

    public MutableLiveData<ArrayList<MovieHome>> getNowPlayingMoviesLive() {
        return mNowPlayingMoviesLive;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
        super.onCleared();
    }
}
