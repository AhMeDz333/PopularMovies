package com.example.ahmed.popularmovies.controller.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ahmed.popularmovies.R;
import com.example.ahmed.popularmovies.controller.adapter.ReviewsAdapter;
import com.example.ahmed.popularmovies.controller.model.Review;
import com.example.ahmed.popularmovies.controller.network.ApiRequests;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ahmed on 12-Mar-17.
 */

public class ReviewsFragment extends RefreshFragment {
	private static final String MOVIE_ID_ARG = "movie_id";

	@BindView(R.id.recycler_review)
	RecyclerView recyclerReview;
	private ReviewsAdapter adapter;

	public static ReviewsFragment newInstance(int movieID) {
		Bundle args = new Bundle();
		args.putInt(MOVIE_ID_ARG, movieID);

		ReviewsFragment fragment = new ReviewsFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.fragment_reviews;
	}
	@Override
	protected void onLayoutInflated(View root, Bundle savedInstanceState) {
		fetchReviews();
	}
	@Override
	protected void onRefresh() {
		fetchReviews();
	}

	private void fetchReviews() {
		int movieID = getActivity().getIntent().getIntExtra(getString(R.string.movie_id_extra), -1);
		ApiRequests
				.getMovieReview(String.valueOf(movieID))
				.subscribe(
						reviews -> bindData(reviews),
						throwable -> {
							throwable.printStackTrace();
							setLoaded(R.string.network_error);
						}
				);
	}

	private void bindData(List<Review> reviews) {
		if (reviews.isEmpty()) {
			setLoaded(R.string.empty_reviews);
			return;
		}
		setLoaded();
		adapter = new ReviewsAdapter(reviews);
		recyclerReview.setLayoutManager(new LinearLayoutManager(context));
		recyclerReview.setAdapter(adapter);
	}
}
