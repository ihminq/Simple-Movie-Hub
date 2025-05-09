package com.ihminq.movie_hub.presentation.userprofile.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ihminq.movie_hub.R;
import com.ihminq.movie_hub.databinding.FragmentEditProfileBinding;


public class EditProfileFragment extends Fragment {
    private FragmentEditProfileBinding mBinding;
    private NavController mNavController;
    private Button mBackBtn;

    public EditProfileFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get NavController
        mNavController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentEditProfileBinding.inflate(inflater, container, false);

        initViews();
        initListeners();
        return mBinding.getRoot();
    }

    private void initListeners() {
        onBtnBackClicked();
    }

    private void onBtnBackClicked() {
        mBackBtn.setOnClickListener(v -> navigateBackToProfile());
    }

    private void navigateBackToProfile() {
        mNavController.popBackStack();
    }

    private void initViews() {
        mBackBtn = mBinding.btnBack;
    }
}