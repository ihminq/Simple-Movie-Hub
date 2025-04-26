package com.ihminq.movie_hub.presentation.movies.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihminq.movie_hub.databinding.FragmentFirstTabBinding;


public class FirstTabFragment extends Fragment {
    private FragmentFirstTabBinding mBinding;

    public FirstTabFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = FragmentFirstTabBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }
}