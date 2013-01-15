package com.yarakyo.cadebuildorders;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainMenu extends Activity {
	//View Variables
	Button Exit;
	Button Terran;
	Button Protoss;
	Button Zerg;
	Button RestoreBuilds;
	
	NotificationManager nm;
	final int uniqueID = 133787;

	public void setUpNotification() {
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Intent intent = new Intent(this, MainMenu.class);
		intent.putExtra("notificationResume", true);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				intent, 0);
		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
				this);
		notificationBuilder.setSmallIcon(R.drawable.sc2cadeicon);
		notificationBuilder.addAction(R.drawable.ic_action_search,
				"Cade Build Orders", pendingIntent);
		notificationBuilder.setContentText("Go to Cade Build Orders App");
		notificationBuilder.setContentIntent(pendingIntent);
		nm.notify(uniqueID, notificationBuilder.build());
	}
	
	private void setUpViewVariables() {
		Exit = (Button) findViewById(R.id.buttonExit);
		Terran = (Button) findViewById(R.id.buttonTerran);
		Protoss = (Button) findViewById(R.id.buttonProtoss);
		Zerg = (Button) findViewById(R.id.buttonZerg);
		RestoreBuilds = (Button) findViewById(R.id.RestoreBuilds);
	}

	private void setUpListeners() {
		
		// Exit Button		
		Exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});

		// Race Buttons
		Terran.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent RaceIntent = new Intent(MainMenu.this, RaceMenu.class);
				RaceIntent.putExtra("race", "Terran");
				startActivity(RaceIntent);
			}
		});

		// Protoss button
		Protoss.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent RaceIntent = new Intent(MainMenu.this, RaceMenu.class);
				RaceIntent.putExtra("race", "Protoss");
				startActivity(RaceIntent);
			}
		});

		//Zerg Button
		Zerg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent RaceIntent = new Intent(MainMenu.this, RaceMenu.class);
				RaceIntent.putExtra("race", "Zerg");
				startActivity(RaceIntent);
			}
		});
		
		//Restore all builds
		RestoreBuilds.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				final AlertDialog.Builder alertConfirmDelete = new AlertDialog.Builder(
						MainMenu.this);
				final AlertDialog alertConfirmDeleteDialog = alertConfirmDelete
						.create();
				alertConfirmDelete.setTitle("Restore Builds");
				alertConfirmDelete
						.setMessage("Are you sure you restore default builds? \n(YOU WILL LOSE ALL YOUR BUILDS)");
				alertConfirmDelete.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								List<Action> allActions = Action.PopulateActions();
								//Write to saveFile
								try {
									FileOutputStream fos = openFileOutput("saveFile", MainMenu.this.MODE_WORLD_WRITEABLE);
									ObjectOutputStream out = new ObjectOutputStream(fos);
									out.writeObject(DefaultBuilds.populateDefaultBuilds(allActions));
									out.close();

								} catch (Exception e) {

								}	
							}
						});
				alertConfirmDelete.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
							}
						});
				alertConfirmDelete.show();
			}
		});
		

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main_menu);
		setUpViewVariables();
		setUpListeners();
		setUpNotification();
	}
	


	@Override
	public void onStart()
	{
		super.onStart();
		checkForResume();
	}

	private void checkForResume() {
		Intent getIntent = getIntent();
		Bundle extras = getIntent.getExtras();
		boolean fromResume = extras.getBoolean("notificationResume");
		if (fromResume == true)
			finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main_menu, menu);
		return true;
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		nm.cancel(uniqueID);
	}

}
