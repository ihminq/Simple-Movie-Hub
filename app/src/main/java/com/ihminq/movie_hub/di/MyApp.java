package com.ihminq.movie_hub.di;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
