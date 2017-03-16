package com.example.ahmed.popularmovies.controller;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.bumptech.glide.Glide;
import com.example.ahmed.popularmovies.controller.helper.ImageLoader;
import com.example.ahmed.popularmovies.controller.model.MovieModel;

import java.io.InputStream;

/**
 * Created by ahmed on 10-Mar-17.
 */
public class MovieApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		initActiveAndroidConfig();
		Glide.get(getApplicationContext()).register(String.class, InputStream.class, new ImageLoader.Factory());
	}

	private void initActiveAndroidConfig() {
		Configuration.Builder config = new Configuration.Builder(getApplicationContext());
		config.addModelClasses(MovieModel.class);
		ActiveAndroid.initialize(config.create());
	}
}
