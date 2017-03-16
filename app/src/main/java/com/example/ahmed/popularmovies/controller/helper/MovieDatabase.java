package com.example.ahmed.popularmovies.controller.helper;

import com.activeandroid.query.Select;
import com.example.ahmed.popularmovies.controller.model.MovieModel;

import java.util.List;

/**
 * Created by ahmed on 12-Mar-17.
 */
public class MovieDatabase {
	private static MovieDatabase instance;

	public static MovieDatabase getInstance() {
		if (instance == null)
			instance = new MovieDatabase();
		return instance;
	}

	private MovieDatabase() {
	}

	public boolean isFavourite(int movieId) {
		MovieModel movie = getMovie(movieId);
		return movie == null? false: movie.isFavourite();
	}

	private MovieModel getMovie(int movieId) {
		return new Select()
				.from(MovieModel.class)
				.where("id = ?", movieId)
				.executeSingle();
	}

	public List<MovieModel> getFavouriteMovies() {
		return new Select()
				.from(MovieModel.class)
				.where("favourite = ?", true)
				.execute();
	}

	public boolean toggleFavourite(MovieModel movie) {
		MovieModel movieModel = getMovie(movie.getMovieId());
		if (movieModel == null) {
			movie.toggleFavourite();
			movie.save();
			return movie.isFavourite();
		} else {
			if (movie != movieModel)
				movie.toggleFavourite();
			movieModel.toggleFavourite();
			movieModel.save();
			return movieModel.isFavourite();
		}
	}
}
