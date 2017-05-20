package com.example.ahmed.popularmovies.callback;

import com.example.ahmed.popularmovies.model.MovieModel;
import com.example.ahmed.popularmovies.view.AspectLockedImageView;

/**
 * Created by ahmed on 10-Mar-17.
 */
public interface MovieEventListener {
	void onMovieSelected(MovieModel movieModel, AspectLockedImageView movieItemImage);
	void onListItemFavouriteStateChanged(MovieModel movieModel, int position);
}
