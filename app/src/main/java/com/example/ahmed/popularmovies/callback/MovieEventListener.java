package com.example.ahmed.popularmovies.callback;

import com.example.ahmed.popularmovies.model.MovieModel;

/**
 * Created by ahmed on 10-Mar-17.
 */
public interface MovieEventListener {
	void onMovieSelected(MovieModel movieModel);
	void onListItemFavouriteStateChanged(MovieModel movieModel, int position);
}
