package com.yarakyo.cadebuildorders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;

import com.yarakyo.cadebuildorders.DefaultBuilds;

public class CadeSplash extends Activity {

	private void SplashScreen() {
		Thread timer = new Thread() {
			public void run() {
				try {
					SystemClock.sleep(500);
				} finally {
					Intent MainMenuIntent = new Intent(CadeSplash.this,
							MainMenu.class);
					MainMenuIntent.putExtra("notificationResume", false);
					startActivity(MainMenuIntent);
				}
			}
		};
		timer.start();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cade_splash);
		SplashScreen();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_cade_splash, menu);
		return true;
	}
}
