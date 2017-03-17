package com.example.ahmed.popularmovies.controller;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.example.ahmed.popularmovies.helper.ImageLoader;

import java.io.InputStream;

/**
 * Created by ahmed on 10-Mar-17.
 */
public class MovieApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Glide.get(getApplicationContext()).register(String.class, InputStream.class, new ImageLoader.Factory());
	}
}
