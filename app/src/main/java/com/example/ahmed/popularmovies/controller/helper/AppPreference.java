package com.example.ahmed.popularmovies.controller.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.ahmed.popularmovies.R;

import io.reactivex.Observable;

/**
 * Created by ahmed on 2/24/2017.
 */

public class AppPreference {
	private static AppPreference instance;
	private final SharedPreferences prefs;
	private Context appContext;

	private AppPreference(Context appContext) {
		this.appContext = appContext;
		prefs = PreferenceManager.getDefaultSharedPreferences(appContext);
	}

	public static AppPreference getInstance(Context appContext) {
		if (instance == null)
			instance = new AppPreference(appContext);
		return instance;
	}

	public Observable<String> getSortTypeObservable() {
		return Observable.just(getSortType());
	}

	public String getSortType() {
		return prefs.getString(
				appContext.getString(R.string.sort_type),
				appContext.getString(R.string.top_rated)
		);
	}

	public void setSortType(String sortType) {
		prefs.edit().putString(appContext.getString(R.string.sort_type), sortType);
	}
}
