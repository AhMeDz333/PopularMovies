package com.example.ahmed.popularmovies.controller.activity;

import android.support.annotation.LayoutRes;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by ahmedz on 11/6/16.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
	@Override
	public void setContentView(@LayoutRes int layoutResID) {
		super.setContentView(layoutResID);
		ButterKnife.bind(this);
	}

	public void showToast(String message) {
		runOnUiThread(() -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show());
	}
	public void showToast(int stringID) {
		showToast(getString(stringID));
	}
}
