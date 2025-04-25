package com.ihminq.movie_hub.presentation.register.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ihminq.movie_hub.databinding.FragmentRegisterBinding;
import com.ihminq.movie_hub.domain.model.auth.User;
import com.ihminq.movie_hub.domain.utils.ValidateResult;
import com.ihminq.movie_hub.presentation.register.viewmodel.RegisterViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding mBinding;
    private RegisterViewModel mRegisterViewModel;
    private NavController mNavController;
    private Button mBackBtn;
    private EditText mEtFullname, mEtEmail, mEtPassword;

    public RegisterFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get NavController
        mNavController = NavHostFragment.findNavController(this);

        //get ViewModel
        mRegisterViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = FragmentRegisterBinding.inflate(inflater, container, false);

        initViews();
        bindData();
        initListeners();
        onRegisterResultChanged();
        return mBinding.getRoot();
    }

    private void onRegisterResultChanged() {
        mRegisterViewModel.getRegisterResultLive().observe(getViewLifecycleOwner(), event -> {
            ValidateResult result = event.getContentIfNotHandled();

            if (result == null) return;

            //if ValidateResult is valid, navigate back to LoginFragment to Login
            if (result.isValid()) {
                Toast.makeText(requireContext(), result.getMessage(), Toast.LENGTH_SHORT).show();

                navigateBackToLogin();
            }
            else { //if ValidateResult is not valid, show Error on EditText
                switch (result.getErrorField()) {
                    case EMAIL:
                        mEtEmail.setError(result.getMessage());
                        mEtEmail.requestFocus();
                        break;
                    case PASSWORD:
                        mEtPassword.setError(result.getMessage());
                        mEtPassword.requestFocus();
                        break;
                    case FULL_NAME:
                        mEtFullname.setError(result.getMessage());
                        mEtFullname.requestFocus();
                    default:
                        Toast.makeText(requireContext(), result.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initListeners() {
        initFullnameInputListener();
        initEmailInputListener();
        initPasswordInputListener();
        initBackToLoginListener();
    }

    private void initFullnameInputListener() {
        // Delete error when User reinput fullname
        mEtFullname.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEtFullname.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void initEmailInputListener() {
        // Delete error when User reinput email
        mEtEmail.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEtEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void initPasswordInputListener() {
        // Delete error when User reinput password
        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mEtPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void initBackToLoginListener() {
        mBackBtn.setOnClickListener(v -> navigateBackToLogin());
    }

    private void navigateBackToLogin() {
        mNavController.popBackStack();
    }

    private void bindData() {
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mBinding.setUser(new User());
        mBinding.setRegisterViewModel(mRegisterViewModel);
    }

    private void initViews() {
        mBackBtn = mBinding.btnBack;
        mEtFullname = mBinding.etFullname;
        mEtEmail = mBinding.etEmail;
        mEtPassword = mBinding.etPassword;
    }
}