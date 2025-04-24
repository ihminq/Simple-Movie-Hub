package com.ihminq.movie_hub.presentation.login.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ihminq.movie_hub.R;
import com.ihminq.movie_hub.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding mBinding;
    private Button mBtnSignIn;
    private TextView mTvRegister;
    private EditText mEtEmail, mEtPassword;
    private NavController mNavController;

    public LoginFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get NavController
        mNavController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentLoginBinding.inflate(inflater, container, false);

        initViews();
        initListeners();
        return mBinding.getRoot();
    }

    private void initListeners() {
        registerClickedListener();
    }

    private void registerClickedListener() {
        mTvRegister.setOnClickListener(v -> navigateToRegister());
    }

    private void navigateToRegister() {
        mNavController.navigate(R.id.action_loginFragment_to_registerFragment);
    }

    private void initViews() {
        mBtnSignIn = mBinding.btnLogin;
        mTvRegister = mBinding.tvRegister;
        mEtEmail = mBinding.etEmail;
        mEtPassword = mBinding.etPassword;
    }
}