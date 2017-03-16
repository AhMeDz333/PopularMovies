package com.example.ahmed.popularmovies.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmed.popularmovies.R;
import com.example.ahmed.popularmovies.controller.callback.TrailerListener;
import com.example.ahmed.popularmovies.controller.model.Trailer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 12-Mar-17.
 */
public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder> {
	private final List<Trailer> trailers;
	private TrailerListener trailerListener;

	public TrailersAdapter(List<Trailer> trailers, TrailerListener trailerListener) {
		this.trailers = trailers;
		this.trailerListener = trailerListener;
	}

	@Override
	public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_row, parent, false);
		return new TrailerViewHolder(view);
	}

	@Override
	public void onBindViewHolder(TrailerViewHolder holder, int position) {
		holder.bindData(trailers.get(position), trailerListener);
	}

	@Override
	public int getItemCount() {
		return trailers.size();
	}

	class TrailerViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.trailer_name)
		TextView trailerName;
		@BindView(R.id.trailer_container)
		View trailerContainer;

		public TrailerViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		public void bindData(Trailer trailer, TrailerListener trailerListener) {
			trailerName.setText(trailer.getName());
			trailerContainer.setOnClickListener(view -> {
				if (trailerListener != null)
					trailerListener.onTrailerSelected(trailer.getKey());
			});
		}
	}
}
