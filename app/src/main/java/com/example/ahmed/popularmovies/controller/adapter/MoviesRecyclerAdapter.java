package com.example.ahmed.popularmovies.controller.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ahmed.popularmovies.R;
import com.example.ahmed.popularmovies.controller.callback.FavouriteStateListener;
import com.example.ahmed.popularmovies.controller.callback.MovieEventListener;
import com.example.ahmed.popularmovies.controller.model.MovieModel;
import com.example.ahmed.popularmovies.controller.view.AspectLockedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 2/25/2017.
 */
public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MovieViewHolder> {
	private final Context context;
	private MovieEventListener eventListener;
	private List<MovieModel> movieList;
	private FavouriteStateListener listener;

	public MoviesRecyclerAdapter(Context context, List<MovieModel> movieList) {
		this.context = context;
		this.movieList = movieList;
	}

	@Override
	public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, null, false);
		return new MovieViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MovieViewHolder holder, int position) {
		MovieModel movieModel = movieList.get(position);
		holder.setMovieItemImage(context, context.getString(R.string.base_image_url) + movieModel.getPosterPath());
		holder.setMovieTitle(movieModel.getTitle());
		holder.setFavourite(position, movieModel.isFavourite(), listener);
		holder.setClickListener(movieModel, eventListener);
	}

	@Override
	public int getItemCount() {
		return movieList.size();
	}

	public void setMovieList(List<MovieModel> movieList) {
		this.movieList = movieList;
	}

	public MovieModel getMovieItem(int position) {
		return movieList.get(position);
	}

	public void setMovieEventListener(MovieEventListener eventListener) {
		this.eventListener = eventListener;
	}

	public void setFavouriteStateListener(FavouriteStateListener listener) {
		this.listener = listener;
	}

	public List<MovieModel> getItems() {
		return movieList;
	}
}

class MovieViewHolder extends RecyclerView.ViewHolder {
	@BindView(R.id.movie_item_image) AspectLockedImageView movieItemImage;
	@BindView(R.id.movie_item_title) TextView movieItemTitle;
	@BindView(R.id.movie_item_genres) TextView movieItemGenres;
	@BindView(R.id.movie_item_btn_favorite) ImageButton movieItemBtnFavorite;
	@BindView(R.id.content_container) View container;

	public MovieViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void setMovieItemImage(Context context, String posterPath) {
		Glide.with(context)
				.load(Uri.parse(posterPath))
				.centerCrop()
				.crossFade()
				.into(movieItemImage);
	}

	public void setMovieTitle(String title) {
		movieItemTitle.setText(title);
	}

	public void setFavourite(int position, boolean isFavourite, FavouriteStateListener listener) {
		movieItemBtnFavorite.setSelected(isFavourite);
		movieItemBtnFavorite.setOnClickListener(view -> {
			if (listener != null)
				listener.onMovieFavouriteStateChanged(position);
		});
	}

	public void setClickListener(MovieModel movieModel, MovieEventListener listener) {
		container.setOnClickListener(view -> {
			if (listener != null)
				listener.onMovieSelected(movieModel);
		});
	}
}