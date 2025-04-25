package com.ihminq.movie_hub.presentation.login.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.ihminq.movie_hub.R;
import com.ihminq.movie_hub.databinding.FragmentLoginBinding;
import com.ihminq.movie_hub.domain.model.auth.User;
import com.ihminq.movie_hub.domain.utils.ValidateResult;
import com.ihminq.movie_hub.presentation.login.viewmodel.LoginViewModel;
import com.ihminq.movie_hub.presentation.utils.SingleEvent;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {
    private FragmentLoginBinding mBinding;
    private LoginViewModel mLoginViewModel;
    private TextView mTvRegister;
    private EditText mEtEmail, mEtPassword;
    private NavController mNavController;

    public LoginFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get NavController
        mNavController = NavHostFragment.findNavController(this);

        //get LoginViewModel
        mLoginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        //check if current user session is not expired
        if (mLoginViewModel.isCurrentUserLoggedIn()) {
            navigateToHome();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentLoginBinding.inflate(inflater, container, false);

        initViews();
        bindData();
        initListeners();
        return mBinding.getRoot();
    }

    private void initListeners() {
        //when user want to Register
        onRegisterClicked();
        //when user click on Login btn
        onLoginResultChanged();
        initEmailInputListener();
        initPasswordInputListener();
    }

    private void onLoginResultChanged() {
        mLoginViewModel.getLoginResultLive().observe(getViewLifecycleOwner(), new Observer<SingleEvent<ValidateResult>>() {
            @Override
            public void onChanged(SingleEvent<ValidateResult> event) {
                ValidateResult result = event.getContentIfNotHandled();

                if (result == null) return;

                //if ValidateResult is valid, navigate to HomeFragment
                if (result.isValid()) {
                    Toast.makeText(requireContext(), result.getMessage(), Toast.LENGTH_SHORT).show();

                    navigateToHome();
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
                        case NONE:
                            mEtEmail.setText("");
                            mEtPassword.setText("");
                            mEtEmail.setError(result.getMessage());
                            mEtPassword.setError(result.getMessage());
                        default:
                            Toast.makeText(requireContext(), result.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void onRegisterClicked() {
        mTvRegister.setOnClickListener(v -> navigateToRegister());
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

    private void navigateToHome() {
        mNavController.navigate(R.id.action_loginFragment_to_homeFragment);
    }

    private void navigateToRegister() {
        mNavController.navigate(R.id.action_loginFragment_to_registerFragment);
    }

    private void bindData() {
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mBinding.setUser(new User());
        mBinding.setLoginViewModel(mLoginViewModel);
    }

    private void initViews() {
        mTvRegister = mBinding.tvRegister;
        mEtEmail = mBinding.etEmail;
        mEtPassword = mBinding.etPassword;
    }
}