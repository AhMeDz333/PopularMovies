package com.example.ahmed.popularmovies.controller.network;

import com.example.ahmed.popularmovies.controller.model.Genre;
import com.example.ahmed.popularmovies.controller.model.MovieModel;
import com.example.ahmed.popularmovies.controller.model.Review;
import com.example.ahmed.popularmovies.controller.model.Trailer;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ahmed on 17/12/16.
 */

public interface ApiInterface {
	@GET("genre/movie/list")
	Observable<Genre.Response> getGenres(@Query("api_key") String apiKey);

    @GET("movie/{type}")
    Observable<MovieModel.MoviesResponse> getMovies(@Path("type") String moviesType, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Observable<Review.ReviewResponse> getMovieReview(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Observable<Trailer.TrailerResponse> getMovieTrailer(@Path("id") String id, @Query("api_key") String apiKey);

}
