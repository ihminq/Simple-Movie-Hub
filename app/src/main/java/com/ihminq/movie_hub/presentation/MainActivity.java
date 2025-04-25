package com.ihminq.movie_hub.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ihminq.movie_hub.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}