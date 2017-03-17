package com.example.ahmed.popularmovies.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ahmed.popularmovies.R;
import com.example.ahmed.popularmovies.callback.FavouriteStateListener;
import com.example.ahmed.popularmovies.controller.fragment.BaseFragment;
import com.example.ahmed.popularmovies.data.MovieDbHelper;
import com.example.ahmed.popularmovies.model.MovieModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieFragment extends BaseFragment {
	public static final String ARG_MOVIE = "arg_movie";
	@BindView(R.id.movie_cover)
	ImageView movieCover;
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.collapsing_toolbar)
	CollapsingToolbarLayout collapsingToolbar;
	@BindView(R.id.app_bar_layout)
	AppBarLayout appBarLayout;
	@BindView(R.id.movie_image)
	ImageView movieImage;
	@BindView(R.id.movie_date)
	TextView movieDate;
	@BindView(R.id.movie_vote_average)
	TextView movieVoteAverage;
	@BindView(R.id.movie_description)
	TextView movieDescription;
	@BindView(R.id.direction_card_view)
	CardView directionCardView;
	@BindView(R.id.scroll)
	NestedScrollView scroll;
	@BindView(R.id.root_detail_layout)
	CoordinatorLayout rootDetailLayout;
	@BindView(R.id.favorite_fab)
	FloatingActionButton favoriteFab;


	private MovieModel movieModel;
	private FavouriteStateListener favouriteListener;

	public MovieFragment() {
		setRetainInstance(true);
	}

	public static MovieFragment newInstance(MovieModel movieModel) {
		Bundle args = new Bundle();
		args.putParcelable(ARG_MOVIE, movieModel);

		MovieFragment fragment = new MovieFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_movie, container, false);
		ButterKnife.bind(this, view);

		movieModel = getArguments().getParcelable(ARG_MOVIE);
//		initToolbar();
		setupViews();
		return view;
	}

	private void initToolbar() {
		getActivity().supportPostponeEnterTransition();
		((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
	}

	@OnClick({R.id.btnReviews, R.id.btnTrailers})
	void onReviewsClicked(View view) {
		Intent intent = new Intent(context, ContentDialogActivity.class);
		intent.putExtra(getString(R.string.movie_id_extra), movieModel.getMovieId());
		intent.putExtra(getString(R.string.type_extra), String.valueOf(view.getTag()));
		startActivity(intent);
	}

	private void setupViews() {
		collapsingToolbar.setTitle(movieModel.getTitle());
		movieVoteAverage.setText(getString(R.string.movie_details_rating, movieModel.getVoteAverage()));
		movieDate.setText((movieModel.getReleaseDate()));
		movieDescription.setText(movieModel.getOverview());
		favoriteFab.setSelected(movieModel.isFavourite());
		favoriteFab.setOnClickListener(view -> {
			MovieDbHelper dbHelper = new MovieDbHelper(context.getApplicationContext());
			if (movieModel.isFavourite())
				dbHelper.deleteFavourite(context, movieModel.getMovieId());
			else
				dbHelper.insertFavourite(context, movieModel);

			movieModel.toggleFavourite();
			view.setSelected(!view.isSelected());

			if (favouriteListener != null)
				favouriteListener.onMovieFavouriteStateChanged(-1);
		});

		Glide.with(this)
				.load(movieModel.getBackdropPath())
				.placeholder(R.color.movie_cover_placeholder)
				.centerCrop()
				.crossFade()
				.into(movieCover);

		Glide.with(this)
				.load(movieModel.getPosterPath())
				.centerCrop()
				.crossFade()
				.into(movieImage);
	}

	public void setFavouriteListener(FavouriteStateListener favouriteListener) {
		this.favouriteListener = favouriteListener;
	}
}
