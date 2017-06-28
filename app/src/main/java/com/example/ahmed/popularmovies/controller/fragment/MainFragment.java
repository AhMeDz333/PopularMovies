package com.example.ahmed.popularmovies.controller.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ahmed.popularmovies.R;
import com.example.ahmed.popularmovies.adapter.MoviesRecyclerAdapter;
import com.example.ahmed.popularmovies.callback.FavouriteStateListener;
import com.example.ahmed.popularmovies.callback.MovieEventListener;
import com.example.ahmed.popularmovies.data.MovieDbHelper;
import com.example.ahmed.popularmovies.helper.AppPreference;
import com.example.ahmed.popularmovies.model.MovieModel;
import com.example.ahmed.popularmovies.network.ApiRequests;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainFragment extends RefreshFragment implements FavouriteStateListener {
	public static final int NUMBER_OF_ROW_ITEMS = 2;
	private static final String MOVIE_LIST = "movie_list";
	private static final java.lang.String SORT_TYPE = "sort_type";

	@BindView(R.id.movies_recycler_view) RecyclerView moviesRecycler;
	MoviesRecyclerAdapter adapter;

	private MovieEventListener eventListener;
	private String sortType;
	private AppPreference appPreference;
	private MovieDbHelper dbHelper;

	public MainFragment() {
	}

	@Override
	protected void onLayoutInflated(View root, Bundle savedInstanceState) {
		appPreference = AppPreference.getInstance(getActivity().getApplicationContext());
		dbHelper = new MovieDbHelper(context.getApplicationContext());
		initMovieRecycler();
		if (savedInstanceState != null) {
			this.sortType = savedInstanceState.getString(SORT_TYPE);
			ArrayList<MovieModel> movies = savedInstanceState.getParcelableArrayList(MOVIE_LIST);
			bindUI(movies);
		}
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.fragment_main;
	}

	@Override
	public void onStart() {
		super.onStart();
		String prefSortType = appPreference.getSortType();
		if (prefSortType.equals(getString(R.string.favorites))) {
			loadFavourites();
			this.sortType = prefSortType;
		} else if (shouldRefresh())
			retrieveMovies();
		else {
			refreshModelFavourites(adapter.getItems());
			adapter.notifyDataSetChanged();
		}
	}

	private void loadFavourites() {
		List<MovieModel> favouriteMovies = dbHelper.getFavouriteMovies(context);
		bindUI(favouriteMovies);
	}

	private boolean shouldRefresh() {
		return adapter.getItemCount() == 0
				|| sortType == null
				|| (!sortType.equals(appPreference.getSortType()));
	}

	private void initMovieRecycler() {
		GridLayoutManager mLayoutManager = new GridLayoutManager(context, NUMBER_OF_ROW_ITEMS);
		adapter = new MoviesRecyclerAdapter(context, new ArrayList<>());
		moviesRecycler.setLayoutManager(mLayoutManager);
		moviesRecycler.setItemAnimator(new DefaultItemAnimator());
		moviesRecycler.setAdapter(adapter);
	}

	@Override
	protected void onRefresh() {
		retrieveMovies();
	}

	private void retrieveMovies() {
		setLoading();
		appPreference
				.getSortTypeObservable()
				.doOnNext(sortType -> this.sortType = sortType)
				.flatMap(ApiRequests::getMovies)
				.doAfterNext(this::refreshModelFavourites)
				.subscribe(
						this::bindUI,
						throwable -> {
							throwable.printStackTrace();
							setLoaded(R.string.network_error);
						}
				);
	}

	private void refreshModelFavourites(List<MovieModel> movies) {
		for (MovieModel movieModel : movies) {
			movieModel.setFavourite(dbHelper.isFavourite(context, movieModel.getMovieId()));
		}
	}

	private void bindUI(List<MovieModel> movies) {
		if (movies.isEmpty()) {
			setLoaded(R.string.empty_favourites);
			return;
		}
		adapter.setMovieList(movies);
		adapter.setFavouriteStateListener(this);
		adapter.setMovieEventListener(eventListener);
		adapter.notifyDataSetChanged();
		setLoaded();
	}

	@Override
	public void onMovieFavouriteStateChanged(int position) {
		MovieModel movieModel = adapter.getMovieItem(position);
		if (movieModel.isFavourite())
			dbHelper.deleteFavourite(context, movieModel.getMovieId());
		else
			dbHelper.insertFavourite(context, movieModel);

		movieModel.toggleFavourite();

		if (sortType.equals(getString(R.string.favorites)))
			loadFavourites();
		else
			adapter.notifyItemChanged(position);

		if (eventListener != null)
			eventListener.onListItemFavouriteStateChanged(movieModel, position);
	}

	public void setMovieEventListener(MovieEventListener eventListener) {
		this.eventListener = eventListener;
		if (adapter != null)
			adapter.setMovieEventListener(eventListener);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelableArrayList(MOVIE_LIST, new ArrayList<>(adapter.getItems()));
		outState.putString(SORT_TYPE, sortType);
	}

	public void notifyDataSetChanged() {
		if (sortType.equals(getString(R.string.favorites)))
			loadFavourites();
		else
			adapter.notifyDataSetChanged();
	}
}
