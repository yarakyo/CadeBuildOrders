package com.yarakyo.cadebuildorders;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.LightingColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RunElement extends RunBuild {

	Action action;
	TextView textViewRunactionDescriptionAndTime;
	ProgressBar progressBar;
	Context context;
	int totalTime;
	boolean expired;

	// Getters
	public TextView getActionDescriptionAndTime() {
		return this.textViewRunactionDescriptionAndTime;
	}

	public ProgressBar getProgressbar() {
		return this.progressBar;
	}

	// Setters
	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public void setProgressBarTime(int currentTime) {
		this.progressBar.setProgress(currentTime);
	}

	// Methods

	private void changeProgressBarColourToRed() {
		Drawable drawable = this.progressBar.getProgressDrawable();
		drawable.setColorFilter(new LightingColorFilter(0xFFff0000, 0xFFff0000));
	}

	public boolean testTimeExpired(int currentTime) {
		if (currentTime > this.totalTime) {
			changeProgressBarColourToRed();
			return true;
		} else {
			return false;
		}
	}

	public boolean testForRemovalTime(int currentTime) {
		if (currentTime > this.totalTime + 5) {
			return true;
		} else {

			return false;
		}

	}

	RunElement(Action action, Context context) {
		this.context = context;
		this.action = action;

		// Initialise all UI Variables
		textViewRunactionDescriptionAndTime = new TextView(context);

		textViewRunactionDescriptionAndTime.setText(action
				.getActionDescription()
				+ " - "
				+ action.getMinutes()
				+ "m "
				+ action.getSeconds() + "s.");

		textViewRunactionDescriptionAndTime.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f);
		
		progressBar = new ProgressBar(context, null,
				android.R.attr.progressBarStyleHorizontal);
		progressBar.setIndeterminate(false);
		progressBar.setVisibility(ProgressBar.VISIBLE);
		progressBar.setProgress(0);
		int totalTime = 0;
		totalTime += action.getMinutes() * 60;
		totalTime += action.getSeconds();
		this.totalTime = totalTime;
		progressBar.setMax(totalTime);

	}

	public void resetProgressBar() {
		int savedTime = progressBar.getMax();				
		progressBar = new ProgressBar(context, null,
				android.R.attr.progressBarStyleHorizontal);
		progressBar.setIndeterminate(false);
		progressBar.setVisibility(ProgressBar.VISIBLE);
		progressBar.setProgress(0);
		progressBar.setMax(savedTime);
		this.expired = false;
	}

}
