package com.example.ahmed.popularmovies.helper;

import android.content.Context;

import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.bumptech.glide.load.model.stream.StreamModelLoader;

import java.io.InputStream;

/**
 * Created by ahmed on 10-Mar-17.
 */

public class ImageLoader extends BaseGlideUrlLoader<String> {
	public static final String BASE_URL = "http://image.tmdb.org/t/p";

	public ImageLoader(Context context) {
		super(context);
	}

	@Override protected String getUrl(String imagePath, int width, int height) {
		String url = buildPosterUrl(imagePath, width);
		return url;
	}

	public static class Factory implements ModelLoaderFactory<String, InputStream> {
		@Override public StreamModelLoader<String> build(Context context, GenericLoaderFactory factories) {
			return new ImageLoader(context);
		}

		@Override public void teardown() { /** ignore */}
	}

	public static String buildPosterUrl(String imagePath, int width) {
		String widthPath;

		if (width <= 92)
			widthPath = "/w92";
		else if (width <= 154)
			widthPath = "/w154";
		else if (width <= 185)
			widthPath = "/w185";
		else if (width <= 342)
			widthPath = "/w342";
		else if (width <= 500)
			widthPath = "/w500";
		else
			widthPath = "/w780";

		return BASE_URL + widthPath + imagePath;
	}
}
