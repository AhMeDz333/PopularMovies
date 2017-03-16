package com.example.ahmed.popularmovies.controller.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ahmed.popularmovies.R;
import com.example.ahmed.popularmovies.controller.adapter.TrailersAdapter;
import com.example.ahmed.popularmovies.controller.callback.TrailerListener;
import com.example.ahmed.popularmovies.controller.model.Trailer;
import com.example.ahmed.popularmovies.controller.network.ApiRequests;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ahmed on 12-Mar-17.
 */

public class TrailersFragment extends RefreshFragment {
	private static final String MOVIE_ID_ARG = "movie_id";
	@BindView(R.id.recycler_trailer)
	RecyclerView recyclerTrailer;

	private TrailerListener trailerListener;
	private TrailersAdapter adapter;

	public static TrailersFragment newInstance(int movieID) {
		Bundle args = new Bundle();
		args.putInt(MOVIE_ID_ARG, movieID);

		TrailersFragment fragment = new TrailersFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.fragment_trailers;
	}
	@Override
	protected void onLayoutInflated(View root, Bundle savedInstanceState) {
		fetchTrailers();
	}
	@Override
	protected void onRefresh() {
		fetchTrailers();
	}

	private void fetchTrailers() {
		int movieID = getActivity().getIntent().getIntExtra(getString(R.string.movie_id_extra), -1);
		ApiRequests
				.getMovieTrailer(String.valueOf(movieID))
				.subscribe(
						trailers -> bindData(trailers),
						throwable -> {
							throwable.printStackTrace();
							setLoaded(R.string.network_error);
						}
				);
	}

	private void bindData(List<Trailer> trailers) {
		if (trailers.isEmpty()) {
			setLoaded(R.string.empty_trailers);
			return;
		}
		setLoaded();
		adapter = new TrailersAdapter(trailers, trailerListener);
		recyclerTrailer.setLayoutManager(new LinearLayoutManager(context));
		recyclerTrailer.setAdapter(adapter);
	}

	public void setTrailerListener(TrailerListener trailerListener) {
		this.trailerListener = trailerListener;
	}
}
