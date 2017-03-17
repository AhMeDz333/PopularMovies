package com.example.ahmed.popularmovies.network;

import com.example.ahmed.popularmovies.model.Genre;
import com.example.ahmed.popularmovies.model.MovieModel;
import com.example.ahmed.popularmovies.model.Review;
import com.example.ahmed.popularmovies.model.Trailer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.ahmed.popularmovies.BuildConfig.MOVIE_DB_API_KEY;


/**
 * Created by ahmed on 12/12/16.
 */

public class ApiRequests {
	private static final int TIMEOUT = 10;

	public static Observable<List<Genre>> getGenres(){
        return ApiClient.getClient()
                .create(ApiInterface.class)
                .getGenres(MOVIE_DB_API_KEY)
		        .map(Genre.Response::getGenres)
		        .timeout(TIMEOUT, TimeUnit.SECONDS)
		        .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

	public static Observable<List<MovieModel>> getMovies(String moviesSortType){
        return ApiClient.getClient()
                .create(ApiInterface.class)
                .getMovies(moviesSortType, MOVIE_DB_API_KEY)
		        .map(MovieModel.MoviesResponse::getResults)
		        .timeout(TIMEOUT, TimeUnit.SECONDS)
		        .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<List<Review>> getMovieReview(String movieId){
        return ApiClient.getClient()
                .create(ApiInterface.class)
                .getMovieReview(movieId, MOVIE_DB_API_KEY)
		        .map(Review.ReviewResponse::getReviews)
		        .timeout(TIMEOUT,TimeUnit.SECONDS)
		        .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<List<Trailer>> getMovieTrailer(String movieId){
        return ApiClient.getClient()
                .create(ApiInterface.class)
                .getMovieTrailer(movieId, MOVIE_DB_API_KEY)
		        .map(Trailer.TrailerResponse::getTrailers)
		        .timeout(TIMEOUT,TimeUnit.SECONDS)
		        .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
