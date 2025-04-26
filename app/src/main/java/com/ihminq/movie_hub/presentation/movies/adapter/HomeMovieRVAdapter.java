package com.ihminq.movie_hub.presentation.movies.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ihminq.movie_hub.databinding.LayoutHomeMovieBinding;
import com.ihminq.movie_hub.databinding.LayoutHomeMovieSkeletonBinding;
import com.ihminq.movie_hub.domain.model.movie.MovieHome;

import java.util.Objects;

public class HomeMovieRVAdapter extends ListAdapter<MovieHome, RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_LOADED = 1;
    private static final int VIEW_TYPE_LOADING = 2;
    private int mCurrentViewType;

    public HomeMovieRVAdapter() {
        super(DIFF_CALLBACK);
        mCurrentViewType = VIEW_TYPE_LOADING;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_LOADED) {
            LayoutHomeMovieBinding binding = LayoutHomeMovieBinding.inflate(layoutInflater, parent, false);
            return new LoadedViewHolder(binding);
        }
        else {
            LayoutHomeMovieSkeletonBinding binding = LayoutHomeMovieSkeletonBinding.inflate(layoutInflater, parent, false);
            return new LoadingViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieHome movie = getItem(position);

        if (holder instanceof LoadedViewHolder) {
            ((LoadedViewHolder) holder).bindMovie(movie);
        }
        else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mCurrentViewType;
    }

    public void notifyDataLoaded() {
        this.mCurrentViewType = VIEW_TYPE_LOADED;
    }

    static class LoadedViewHolder extends RecyclerView.ViewHolder {
        private final LayoutHomeMovieBinding binding;
        public LoadedViewHolder(@NonNull LayoutHomeMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindMovie(MovieHome movieHome) {
            binding.setMovie(movieHome);
        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(@NonNull LayoutHomeMovieSkeletonBinding binding) {
            super(binding.getRoot());
        }
    }

    private static final DiffUtil.ItemCallback<MovieHome> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieHome oldItem, @NonNull MovieHome newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull MovieHome oldItem, @NonNull MovieHome newItem) {
            return oldItem.equals(newItem);
        }
    };
}
