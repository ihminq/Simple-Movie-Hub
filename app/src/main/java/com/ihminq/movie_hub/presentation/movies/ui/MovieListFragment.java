package com.ihminq.movie_hub.presentation.movies.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ihminq.movie_hub.databinding.FragmentMovieListBinding;
import com.ihminq.movie_hub.domain.model.movie.MovieHome;
import com.ihminq.movie_hub.presentation.home.viewmodel.HomeViewModel;
import com.ihminq.movie_hub.presentation.movies.adapter.HomeMovieRVAdapter;
import com.ihminq.movie_hub.presentation.movies.viewmodel.HomeMovieViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MovieListFragment extends Fragment {
    private FragmentMovieListBinding mBinding;
    private HomeViewModel mHomeViewModel;
    private HomeMovieViewModel mHomeMovieViewModel;
    private NavController mNavController;
    private EditText mEtSearchBar;
    private RecyclerView mPopularRecyclerView, mTopRatedRecyclerView, mNowPlayingRecyclerView, mUpComingRecyclerView, mSearchResultRecyclerView;
    private HomeMovieRVAdapter mPopularAdapter, mTopRatedAdapter, mUpcomingAdapter, mNowPlayingAdapter;

    public MovieListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get ViewModel
        mHomeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        mHomeMovieViewModel = new ViewModelProvider(requireActivity()).get(HomeMovieViewModel.class);

        //get NavController
        mNavController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMovieListBinding.inflate(inflater, container, false);

        initViews();
        bindData();
        initListeners();
        return mBinding.getRoot();
    }

    private void initListeners() {
        initSearchBarChangedListener();
        initHomeMoviesLiveListener();
    }

    private void initHomeMoviesLiveListener() {
        onPopularMoviesLiveChanged();
        onTopRatedMoviesLiveChanged();
        onUpComingMoviesLiveChanged();
        onNowPlayingMoviesLiveChanged();
    }

    private void onPopularMoviesLiveChanged() {
        mHomeMovieViewModel.getPopularMoviesLive().observe(getViewLifecycleOwner(), new Observer<>() {
            @Override
            public void onChanged(ArrayList<MovieHome> movieHomes) {
                if (movieHomes == null || movieHomes.isEmpty()) {
                    mPopularAdapter.submitList(null);
                }
                else {
                    mPopularAdapter.notifyDataLoaded();
                    mPopularAdapter.submitList(movieHomes);
                }
            }
        });
    }

    private void onTopRatedMoviesLiveChanged() {
        mHomeMovieViewModel.getTopRatedMoviesLive().observe(getViewLifecycleOwner(), new Observer<>() {
            @Override
            public void onChanged(ArrayList<MovieHome> movieHomes) {
                if (movieHomes == null || movieHomes.isEmpty()) {
                    mTopRatedAdapter.submitList(null);
                }
                else {
                    mTopRatedAdapter.notifyDataLoaded();
                    mTopRatedAdapter.submitList(movieHomes);
                }
            }
        });
    }

    private void onNowPlayingMoviesLiveChanged() {
        mHomeMovieViewModel.getNowPlayingMoviesLive().observe(getViewLifecycleOwner(), new Observer<>() {
            @Override
            public void onChanged(ArrayList<MovieHome> movieHomes) {
                if (movieHomes == null || movieHomes.isEmpty()) {
                    mNowPlayingAdapter.submitList(null);
                }
                else {
                    mNowPlayingAdapter.notifyDataLoaded();
                    mNowPlayingAdapter.submitList(movieHomes);
                }
            }
        });
    }

    private void onUpComingMoviesLiveChanged() {
        mHomeMovieViewModel.getUpcomingMoviesLive().observe(getViewLifecycleOwner(), new Observer<>() {
            @Override
            public void onChanged(ArrayList<MovieHome> movieHomes) {
                if (movieHomes == null || movieHomes.isEmpty()) {
                    mUpcomingAdapter.submitList(null);
                }
                else {
                    mUpcomingAdapter.notifyDataLoaded();
                    mUpcomingAdapter.submitList(movieHomes);
                }
            }
        });
    }

    private void initSearchBarChangedListener() {
        mEtSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().isEmpty()) {
                    mSearchResultRecyclerView.setVisibility(View.GONE);
                }
                else {
                    mSearchResultRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void bindData() {
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mBinding.setUser(mHomeViewModel.getCurrentUserLive().getValue());
    }

    private void setupRecyclerViews() {
        //set layout managers
        mPopularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mTopRatedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mUpComingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mNowPlayingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        //create adapters
        mPopularAdapter = new HomeMovieRVAdapter();
        mTopRatedAdapter = new HomeMovieRVAdapter();
        mUpcomingAdapter = new HomeMovieRVAdapter();
        mNowPlayingAdapter = new HomeMovieRVAdapter();

        //set adapters
        mPopularRecyclerView.setAdapter(mPopularAdapter);
        mTopRatedRecyclerView.setAdapter(mTopRatedAdapter);
        mUpComingRecyclerView.setAdapter(mUpcomingAdapter);
        mNowPlayingRecyclerView.setAdapter(mNowPlayingAdapter);
    }

    private void initViews() {
        mEtSearchBar = mBinding.etSearchBar;
        mSearchResultRecyclerView = mBinding.rvSearchResult;
        mPopularRecyclerView = mBinding.rvPopular;
        mTopRatedRecyclerView = mBinding.rvToprated;
        mNowPlayingRecyclerView = mBinding.rvNowplaying;
        mUpComingRecyclerView = mBinding.rvUpcoming;

        setupRecyclerViews();
    }
}