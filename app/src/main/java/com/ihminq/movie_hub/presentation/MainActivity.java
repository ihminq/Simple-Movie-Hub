package com.ihminq.movie_hub.presentation;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.ihminq.movie_hub.R;
import com.ihminq.movie_hub.presentation.auth.viewmodel.AuthViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get NavController
        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_nav_host);
        mNavController = navHost.getNavController();
        AuthViewModel authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        authViewModel.getLoggedOutStatusLive().observe(this, isOut -> {
            if (Boolean.TRUE.equals(isOut)) {
//                // pop tất cả destinations trả về startDestination (loginFragment)
//                mNavController.popBackStack(
//                        /* destinationId = */ mNavController.getGraph().getStartDestinationId(),
//                        /* inclusive    = */ false
//                );
                Log.d("MAIN", "logout");
                int graphId = mNavController.getGraph().getId();
                mNavController.clearBackStack(graphId);
                mNavController.navigate(R.id.loginFragment);
            }
            else {
                Toast.makeText(this, "Logout failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}