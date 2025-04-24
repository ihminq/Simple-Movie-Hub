package com.ihminq.movie_hub.presentation.register.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.ihminq.movie_hub.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding mBinding;
    private NavController mNavController;
    private Button mBackBtn;
    private EditText mEtFullname, mEtEmail, mEtPassword;

    public RegisterFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get NavController
        mNavController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = FragmentRegisterBinding.inflate(inflater, container, false);

        initViews();
        initListeners();
        return mBinding.getRoot();
    }

    private void initListeners() {
        mBackBtn.setOnClickListener(v -> navigateBackToLogin());
    }

    private void navigateBackToLogin() {
        mNavController.popBackStack();
    }

    private void initViews() {
        mBackBtn = mBinding.btnBack;
        mEtFullname = mBinding.etFullname;
        mEtEmail = mBinding.etEmail;
        mEtPassword = mBinding.etPassword;
    }
}