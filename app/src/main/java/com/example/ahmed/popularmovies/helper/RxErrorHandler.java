package com.example.ahmed.popularmovies.helper;

import android.content.Context;
import android.widget.Toast;

import io.reactivex.functions.Consumer;

/**
 * Created by ahmed on 2/25/2017.
 */
public class RxErrorHandler implements Consumer<Throwable> {
	private Context context;

	public RxErrorHandler(Context context) {
		this.context = context;
	}

	@Override
	public void accept(Throwable throwable) throws Exception {
		throwable.printStackTrace();
		Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
	}
}
