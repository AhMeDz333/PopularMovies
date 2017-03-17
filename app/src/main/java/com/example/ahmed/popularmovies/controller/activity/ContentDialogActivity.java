package com.example.ahmed.popularmovies.controller.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.ahmed.popularmovies.R;
import com.example.ahmed.popularmovies.callback.TrailerListener;
import com.example.ahmed.popularmovies.controller.fragment.ReviewsFragment;
import com.example.ahmed.popularmovies.controller.fragment.TrailersFragment;

public class ContentDialogActivity extends BaseActivity implements TrailerListener {
	private static final String CONTENT_FRAGMENT_TAG = "content_fragment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content_dialog);

		String contentType = getIntent().getStringExtra(getString(R.string.type_extra));
		int movieID = getIntent().getIntExtra(getString(R.string.movie_id_extra), -1);
		setTitle(contentType);

		if (savedInstanceState == null) {
			Fragment fragment;
			if (contentType.equals(getString(R.string.movie_reviews))) {
				fragment = ReviewsFragment.newInstance(movieID);
			} else {
				TrailersFragment trailersFragment = TrailersFragment.newInstance(movieID);
				trailersFragment.setTrailerListener(this);
				fragment = trailersFragment;
			}
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.fragment_container, fragment, CONTENT_FRAGMENT_TAG)
					.commit();
		} else {
			Fragment fragment = getSupportFragmentManager().findFragmentByTag(CONTENT_FRAGMENT_TAG);
			if (fragment instanceof TrailersFragment)
				((TrailersFragment) fragment).setTrailerListener(this);
		}
	}

	@Override
	public void onTrailerSelected(String url) {
		Uri uri = Uri.parse(getString(R.string.base_trailer_url, url));
		Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
		if (sendIntent.resolveActivity(getPackageManager()) != null) {
			startActivity(sendIntent);
		}
	}
}
