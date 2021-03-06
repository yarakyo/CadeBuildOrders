package com.yarakyo.cadebuildorders;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import com.yarakyo.cadebuildorders.ActionList.Race;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Build extends Activity {
	ScrollView scrollViewActionsList;
	LinearLayout SVActionLL;
	LinearLayout SVActionLLLeft;
	LinearLayout SVActionLLRight;
	Button buttonAddAction;
	Button buttonRemove;
	Button buttonFinish;
	RadioGroup radioActionList;
	List<Action> actionArray;
	List<Action> buildOrderActionList;
	TextView textViewActionList;
	ImageView actionImage;
	BuildMenuHandler BMH;

	String buildName;
	Race race;
	String raceString;

	// Getter
	public int getBuildOrderActionListSize() {
		return this.buildOrderActionList.size();
	}

	public RadioGroup getRadioActionList() {
		return this.radioActionList;
	}

	public List<Action> getBuildOrderActionList() {
		return this.buildOrderActionList;
	}

	public String getBuildName() {
		return this.buildName;
	}

	public Race getRace() {
		return this.race;
	}

	private String getRaceString() {
		return this.raceString;
	}

	// Setter
	public void addBuildOrderActionToList(Action actionToAdd) {
		this.buildOrderActionList.add(actionToAdd);
	}

	public void setNewbuildOrderActionList(List<Action> passedBuild) {
		this.buildOrderActionList = passedBuild;
	}

	// Sorting method
	public List<Integer> bubbleSortActionList(List<Integer> keys,
			List<Integer> values) {

		// Implementation of bubble sort and save indices
		boolean swap = true;
		while (swap == true) {
			swap = false;
			for (int i = 1; i < values.size(); i++) {
				if (values.get(i - 1) > values.get(i)) {
					int a = values.get(i - 1);
					int b = values.get(i);
					values.set(i, a);
					values.set(i - 1, b);
					int c = keys.get(i - 1);
					int d = keys.get(i);
					keys.set(i, c);
					keys.set(i - 1, d);
					swap = true;
				}
			}
		}

		return keys;

	}

	private void setRace(String race) {
		if (race.equals("Terran"))
			this.race = Race.Terran;
		if (race.equals("Protoss"))
			this.race = Race.Protoss;
		if (race.equals("Zerg"))
			this.race = Race.Zerg;
	}

	private void SortBuildOrderByTime() {

		// Put into List the total Time and set reference in another List

		Iterator<Action> BOActionListIterator = buildOrderActionList.iterator();
		List<Integer> keys = new ArrayList<Integer>();
		List<Integer> values = new ArrayList<Integer>();

		int index = 0;
		// Calcualte time and set Index
		while (BOActionListIterator.hasNext()) {
			Action temp = BOActionListIterator.next();
			int totalTimeInSeconds = 0;
			totalTimeInSeconds += temp.getMinutes() * 60;
			totalTimeInSeconds += temp.getSeconds();
			values.add(totalTimeInSeconds);
			keys.add(index);
			index++;
		}

		List<Integer> sortedKeys = bubbleSortActionList(keys, values);
		Iterator<Integer> sortedKeysIterator = sortedKeys.iterator();
		List<Action> sortedActionList = new ArrayList<Action>();
		while (sortedKeysIterator.hasNext()) {
			int tempEntry = (int) sortedKeysIterator.next();
			sortedActionList.add(buildOrderActionList.get(tempEntry));
		}

		buildOrderActionList = sortedActionList;

	}

	private void setUpListenersAndVariables() {

		// Populate actionList
		actionArray = Action.PopulateActions();

		// Set Build Order Action List
		buildOrderActionList = new ArrayList<Action>();

		// Set Radio button List
		radioActionList = new RadioGroup(this);

		// Set up scrollView
		scrollViewActionsList = (ScrollView) findViewById(R.id.scrollViewActionsList);

		// Set up Buttons
		buttonAddAction = (Button) findViewById(R.id.buttonAddAction);
		buttonAddAction.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent AddAction = new Intent(Build.this, BuildAdd.class);
				AddAction.putExtra("race", getRaceString());
				startActivityForResult(AddAction, 1);
			}

		});

		buttonRemove = (Button) findViewById(R.id.buttonRemoveAction);
		buttonRemove.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int checkedRadioID = BMH.getCheckedRadioButton();
				deleteActionAndRefreshList(checkedRadioID);
			}
		});

		buttonFinish = (Button) findViewById(R.id.buttonFinishBuild);
		buttonFinish.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				Intent closeIntent = new Intent();
				ActionList passableBuildOrder = new ActionList(Build.this
						.getBuildOrderActionList(), Build.this.getBuildName(),
						Build.this.race);
				bundle.putSerializable("BuildOrder", passableBuildOrder);
				closeIntent.putExtras(bundle);
				setResult(RESULT_OK, closeIntent);
				Build.this.finish();
			}
		});

	}


	private void RedrawRadioList() {
		scrollViewActionsList.removeAllViews();
		BMH = new BuildMenuHandler(buildOrderActionList, this);
		scrollViewActionsList.addView(BMH.getMenu());
	}

	private void deleteActionAndRefreshList(int deleteActionID) {
		Iterator<Action> BOiterator = buildOrderActionList.iterator();
		List<Action> tempBOList = new ArrayList<Action>();
		int counter = 0;
		while (BOiterator.hasNext()) {
			Action temp = BOiterator.next();
			if (counter == deleteActionID) {
				counter++;
				continue;
			}
			tempBOList.add(temp);
			counter++;
		}

		buildOrderActionList = tempBOList;

		SortBuildOrderByTime();

		// Redraw Radio
		RedrawRadioList();
	}

	private void populateWithExisitingBuild(ActionList passedBuild) {
		List<Action> populateBuild = passedBuild.getActionList();
		setNewbuildOrderActionList(populateBuild);
		RedrawRadioList();
	}

	private void handleAddActivityResult(Intent savedIntent) {

		Bundle savedBundle = savedIntent.getExtras();
		int savedID = savedBundle.getInt("Action");
		Action temp = Action.getActionByID(actionArray, savedID);
		int minutes = savedBundle.getInt("Minutes");
		int seconds = savedBundle.getInt("Seconds");
		int supply = savedBundle.getInt("Supply");
		Action newAction = new Action(temp, minutes, seconds,supply);

		// Add to List
		addBuildOrderActionToList(newAction);

		// Sort the List
		SortBuildOrderByTime();

		// Redraw Radio
		RedrawRadioList();
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent savedIntent) {
		// Check for new Actions
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				handleAddActivityResult(savedIntent);
			}
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_build);

		setUpListenersAndVariables();

		// Set build name and race name
		Intent buildIntent = getIntent();
		this.buildName = buildIntent.getStringExtra("BuildName");
		this.raceString = buildIntent.getStringExtra("race");
		setRace(this.raceString);
		textViewActionList = (TextView) findViewById(R.id.textViewActionList);
		textViewActionList.setText(buildName + " - Build Order:");

		// Handle caller
		String intentType = buildIntent.getStringExtra("IntentType");
		if (intentType.equals("AddBuild")) {
			// Blank Build
		}

		if (intentType.equals("EditBuild")) {
			// Edit Build
			ActionList passedBuild = (ActionList) buildIntent
					.getSerializableExtra("BuildOrder");
			populateWithExisitingBuild(passedBuild);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_build, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		final AlertDialog.Builder alertConfirmBack = new AlertDialog.Builder(
				this);
		final AlertDialog alertConfirmBackDialog = alertConfirmBack
				.create();
		alertConfirmBack.setTitle("Cancel Changes");
		alertConfirmBack
				.setMessage("This build is unsaved, do you want to go back? \n(Any changes will be lost)");
		alertConfirmBack.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent closeIntent = new Intent();
						setResult(RESULT_CANCELED, closeIntent);
						Build.this.finish();
					}
				});
		alertConfirmBack.setNegativeButton("No",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		alertConfirmBack.show();
	}


}
