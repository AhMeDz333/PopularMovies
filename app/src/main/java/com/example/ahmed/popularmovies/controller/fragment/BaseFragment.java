package com.example.ahmed.popularmovies.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.ahmed.popularmovies.controller.helper.RxErrorHandler;

/**
 * Created by ahmed on 12/28/2016.
 */

public class BaseFragment extends Fragment {
	protected RxErrorHandler errorHandler;
	protected Context context;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
		errorHandler = new RxErrorHandler(context);
	}
}
