package com.example.ahmed.popularmovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmed.popularmovies.R;
import com.example.ahmed.popularmovies.model.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 12-Mar-17.
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {
	private final List<Review> reviews;

	public ReviewsAdapter(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_row, parent, false);
		return new ReviewViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ReviewViewHolder holder, int position) {
		holder.bindData(reviews.get(position));
	}

	@Override
	public int getItemCount() {
		return reviews.size();
	}

	class ReviewViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.author_name)
		TextView authorName;
		@BindView(R.id.content)
		TextView content;

		public ReviewViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		public void bindData(Review review) {
			authorName.setText(review.getAuthor());
			content.setText(review.getContent());
		}
	}
}
