package com.example.ahmed.popularmovies.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ahmed.popularmovies.R;

import butterknife.ButterKnife;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by ahmed on 12/9/2016.
 */

public abstract class RefreshFragment extends BaseFragment {
	private SwipeRefreshLayout refreshLayout;
	private View contentView;
	private TextView emptyMessage;
	protected Context context;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.base_fragment, container, false);
		FrameLayout contentContainer = (FrameLayout) root.findViewById(R.id.content_container);
		contentView = inflater.inflate(getLayoutResId(), null);
		contentContainer.addView(contentView, 0, new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
		initViews(root);
		setLoading();
		ButterKnife.bind(this, root);
		onLayoutInflated(root, savedInstanceState);
		return root;
	}

	protected abstract void onLayoutInflated(View root, Bundle savedInstanceState);

	protected abstract int getLayoutResId();

	private void initViews(View root) {
		emptyMessage = (TextView) root.findViewById(R.id.empty_message);
		refreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe_container);
		refreshLayout.setOnRefreshListener(() -> onRefresh());
	}
	protected void setLoading() {
		contentView.setVisibility(View.GONE);
		emptyMessage.setVisibility(View.GONE);
		refreshLayout.setRefreshing(true);
	}
	protected void setLoaded(int stringResId) {
		contentView.setVisibility(View.GONE);
		emptyMessage.setVisibility(View.VISIBLE);
		emptyMessage.setText(stringResId);
		refreshLayout.setRefreshing(false);
	}
	protected void setLoaded() {
		contentView.setVisibility(View.VISIBLE);
		emptyMessage.setVisibility(View.GONE);
		refreshLayout.setRefreshing(false);
	}

	protected abstract void onRefresh();
}