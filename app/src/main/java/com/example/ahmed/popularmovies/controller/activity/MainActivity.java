package com.example.ahmed.popularmovies.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ahmed.popularmovies.R;
import com.example.ahmed.popularmovies.callback.FavouriteStateListener;
import com.example.ahmed.popularmovies.callback.MovieEventListener;
import com.example.ahmed.popularmovies.controller.fragment.MainFragment;
import com.example.ahmed.popularmovies.model.MovieModel;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MovieEventListener, FavouriteStateListener {
	private static final String MOVIES_FRAGMENT_TAG = "movies_fragment";
	private static final String MOVIE_DETAIL_FRAGMENT_TAG = "movie_detail_fragment";
	private static final String MOVIE_EMPTY_FRAGMENT_TAG = "movie_empty_fragment";

	@BindView(R.id.toolbar) Toolbar toolbar;
	@Nullable
	@BindView(R.id.movie_details_container)
	View detailsContainer;

	private boolean tabletMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setSupportActionBar(toolbar);
		tabletMode = detailsContainer != null;

		MainFragment mainFragment;
		if (savedInstanceState == null) {
			mainFragment = new MainFragment();
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			if (tabletMode)
				transaction
						.replace(R.id.movie_list_container, mainFragment, MOVIES_FRAGMENT_TAG)
						.replace(R.id.movie_details_container, new Fragment(), MOVIE_EMPTY_FRAGMENT_TAG)
						.commit();
			else
				transaction
						.replace(R.id.movie_list_container, mainFragment, MOVIES_FRAGMENT_TAG)
						.commit();
		} else {
			mainFragment = findMainFragment();
			if (tabletMode) {
				MovieFragment movieFragment = findMovieFragment();
				if (movieFragment != null)
					movieFragment.setFavouriteListener(this);
			}
		}
		mainFragment.setMovieEventListener(this);
	}

	@Override
	public void onMovieSelected(MovieModel movieModel) {
		if (!tabletMode) {
			Intent intent = new Intent(this, MovieActivity.class);
			intent.putExtra(MovieActivity.EXTRA_MOVIE, movieModel);
			startActivity(intent);
		} else {
			replaceMovieFragment(movieModel);
		}
	}

	@Override
	public void onListItemFavouriteStateChanged(MovieModel movieModel, int position) {
		if (tabletMode) {
			replaceMovieFragment(movieModel);
		}
	}

	@Override
	public void onMovieFavouriteStateChanged(int position) {
		MainFragment mainFragment = findMainFragment();
		if (mainFragment != null) {
			mainFragment.notifyDataSetChanged();
		}
	}

	private void replaceMovieFragment(MovieModel movieModel) {
		MovieFragment movieFragment = MovieFragment.newInstance(movieModel);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.movie_details_container, movieFragment, MOVIE_DETAIL_FRAGMENT_TAG)
				.commit();
		movieFragment.setFavouriteListener(this);
	}

	private MainFragment findMainFragment() {
		return (MainFragment) getSupportFragmentManager().findFragmentByTag(MOVIES_FRAGMENT_TAG);
	}

	private MovieFragment findMovieFragment() {
		return (MovieFragment) getSupportFragmentManager().findFragmentByTag(MOVIE_DETAIL_FRAGMENT_TAG);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			startActivity(new Intent(this, SettingsActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
