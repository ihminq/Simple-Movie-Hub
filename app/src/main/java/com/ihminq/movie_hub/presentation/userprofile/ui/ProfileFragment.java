package com.ihminq.movie_hub.presentation.userprofile.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ihminq.movie_hub.R;
import com.ihminq.movie_hub.databinding.FragmentProfileBinding;
import com.ihminq.movie_hub.presentation.auth.viewmodel.AuthViewModel;
import com.ihminq.movie_hub.presentation.home.viewmodel.HomeViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {
    private FragmentProfileBinding mBinding;
    private AuthViewModel mAuthViewModel;
    private HomeViewModel mHomeViewModel;
    private NavController mNavController;
    private Button mBtnEdit, mBtnLogout;

    public ProfileFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get ViewModel
        mAuthViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        mHomeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        //get NavController
        mNavController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentProfileBinding.inflate(inflater, container, false);

        initViews();
        bindData();
        initListeners();
        return mBinding.getRoot();
    }

    private void initListeners() {
        onBtnEditClicked();
        onBtnLogoutClicked();
    }

    private void onBtnLogoutClicked() {
        mBtnLogout.setOnClickListener(v -> mAuthViewModel.logout());
    }


    private void onBtnEditClicked() {
        mBtnEdit.setOnClickListener(v -> navigateToEditProfileFragment());
    }

    private void navigateToEditProfileFragment() {
        mNavController.navigate(R.id.action_profileFragment_to_editProfileFragment);
    }

    private void bindData() {
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mBinding.setUser(mHomeViewModel.getCurrentUserLive().getValue());
    }

    private void initViews() {
        mBtnEdit = mBinding.btnEdit;
        mBtnLogout = mBinding.btnLogout;
    }
}