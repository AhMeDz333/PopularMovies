package com.example.ahmed.popularmovies.controller.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.ahmed.popularmovies.R;
import com.example.ahmed.popularmovies.controller.model.MovieModel;

import butterknife.BindView;

public class MovieActivity extends BaseActivity {
	public static final String EXTRA_MOVIE = "EXTRA_MOVIE";
	private static final String MOVIE_FRAGMENT_TAG = "movie_fragment";

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		MovieModel movieModel = getIntent().getParcelableExtra(EXTRA_MOVIE);

		if (savedInstanceState == null) {
			MovieFragment fragment = MovieFragment.newInstance(movieModel);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.movie_details_container, fragment, MOVIE_FRAGMENT_TAG)
					.commit();
		}
	}
}
