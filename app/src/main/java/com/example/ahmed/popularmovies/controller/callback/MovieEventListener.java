package com.example.ahmed.popularmovies.controller.callback;

import com.example.ahmed.popularmovies.controller.model.MovieModel;

/**
 * Created by ahmed on 10-Mar-17.
 */
public interface MovieEventListener {
	void onMovieSelected(MovieModel movieModel);
	void onListItemFavouriteStateChanged(MovieModel movieModel, int position);
}
