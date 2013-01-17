package com.yarakyo.cadebuildorders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yarakyo.cadebuildorders.ActionList.Category;
import com.yarakyo.cadebuildorders.ActionList.Race;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class BuildAdd extends Activity {

	List<Action> actionArray;

	// Action Related Variables
	Race currentRace;
	Category currentCategory;
	int currentActionID;

	// Timing Variables
	int currentMinutes;
	int currentSeconds;
	EditText editTextTimeMinutes;
	EditText editTextTimeSeconds;

	// Buttons
	Button buttonAddActionOK;
	Button buttonAddActionCancel;

	// Getters
	public int getCurrentActionID() {
		return this.currentActionID;
	}

	public int getCurrentMinutes() {
		return this.currentMinutes;
	}

	public int getCurrentSeconds() {
		return this.currentSeconds;
	}

	// Setters
	public void setMinutes(int minutes) {
		this.currentMinutes = minutes;
	}

	public void setSeconds(int seconds) {
		this.currentSeconds = seconds;
	}

	private int LookUpActionID(List<Action> actionArray, String actionString) {
		int ID = -1;
		Iterator<Action> actionArrayIterator = actionArray.iterator();
		while (actionArrayIterator.hasNext()) {
			Action Temp = actionArrayIterator.next();
			if (Temp.getActionDescription().equals(actionString)) {
				ID = Temp.getActionID();
				break;
			}
		}
		return ID;
	}

	private List<String> FilterActions(Race Race, Category Category,
			List<Action> actionArray) {
		List<String> filteredActions = new ArrayList<String>();
		Iterator<Action> actionArrayIterator = actionArray.iterator();
		while (actionArrayIterator.hasNext()) {
			Action actionTemp = actionArrayIterator.next();
			if (actionTemp.race.equals(Race)
					&& actionTemp.category.equals(Category)) {
				filteredActions.add(actionTemp.actionDescription);
			}
		}
		return filteredActions;
	}

	private void RaceSpinnerChange(Object item, int position) {
		Race selectedRace = Race.Terran;
		switch (position) {
		case 0:
			selectedRace = Race.Terran;
			break;
		case 1:
			selectedRace = Race.Protoss;
			break;
		case 2:
			selectedRace = Race.Zerg;
			break;
		}

		this.currentRace = selectedRace;

		// Set the Race Spinner
		Spinner raceSpinner = (Spinner) findViewById(R.id.spinnerRace);
		raceSpinner.setSelection(position);

		// Set the Category Spinner
		setUpCatagorySpinner();

		// Set the Action Spinner
		setUpActionSpinner(currentRace,Category.Building);

	}

	private void CategorySpinnerChange(Object item, int position) {
		Category selectedCategory = Category.Building;
		switch (position) {
		case 0:
			selectedCategory = Category.Building;
			break;
		case 1:
			selectedCategory = Category.Unit;
			break;
		case 2:
			selectedCategory = Category.Action;
			break;
		}

		this.currentCategory = selectedCategory;

		// Set the Action Spinner
		setUpActionSpinner(this.currentRace,this.currentCategory);
	}

	private void ActionSpinnerChange(Object item, int position) {
		String lookUpActionString = item.toString();
		this.currentActionID = LookUpActionID(this.actionArray,
				lookUpActionString);
	}

	private void SetupSpinnersAndDefaultSpinnerValues() {
		// Set the Race Spinner
		setUpRaceSpinner();
		
		// Set the Category Spinner
		setUpCatagorySpinner();
		
		// Set the Action Spinner
		setUpActionSpinner(Race.Terran,Category.Building);
	}

	private void setUpCatagorySpinner() {
		Spinner categorySpinner = (Spinner) findViewById(R.id.spinnerCategory);
		List<String> categoryList = new ArrayList<String>();
		categoryList.add(Category.Building.toString());
		categoryList.add(Category.Unit.toString());
		categoryList.add(Category.Action.toString());
		ArrayAdapter<String> categorySpinnerAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, categoryList);
		categorySpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_item);
		categorySpinner.setAdapter(categorySpinnerAdapter);
		categorySpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						Object item = parent.getItemAtPosition(position);
						CategorySpinnerChange(item, position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

	}

	private void setUpRaceSpinner() {
		Spinner raceSpinner = (Spinner) findViewById(R.id.spinnerRace);
		List<String> raceArrayList = new ArrayList<String>();
		raceArrayList.add(Race.Terran.toString());
		raceArrayList.add(Race.Protoss.toString());
		raceArrayList.add(Race.Zerg.toString());
		ArrayAdapter<String> raceSpinnerAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, raceArrayList);
		raceSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_item);
		raceSpinner.setAdapter(raceSpinnerAdapter);
		raceSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						Object item = parent.getItemAtPosition(position);
						RaceSpinnerChange(item, position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});
	}

	private void setUpActionSpinner(Race race,Category category) {
		Spinner actionSpinner = (Spinner) findViewById(R.id.spinnerAction);
		List<String> actionList = FilterActions(race, category,
				actionArray);

		BuildAdapter buildAdapater = new BuildAdapter(this,
				R.layout.spinner_row, actionList, actionArray);
		actionSpinner.setAdapter(buildAdapater);
		actionSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						Object item = parent.getItemAtPosition(position);
						ActionSpinnerChange(item, position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
	}

	private void setSelectedRace() {
		Intent buildIntent = getIntent();
		String race = buildIntent.getStringExtra("race");
		int raceInt = 0;
		if (race.equals(null)) {
			return;
		}

		if (race.equals("Terran")) {
			raceInt = 0;
		}

		if (race.equals("Protoss")) {
			raceInt = 1;
		}

		if (race.equals("Zerg")) {
			raceInt = 2;
		}

		Object tempObject = new Object();
		RaceSpinnerChange(tempObject, raceInt);
	}

	private void setUpListenersAndVariables() {
		// Set Time Values
		setDefaultTimeAndRules();

		// Button Listeners
		setButtonListeners();

		// Set Race to match upper levels
		 setSelectedRace();
	}

	public void setButtonListeners() {
		buttonAddActionOK = (Button) findViewById(R.id.buttonBuildAddActionOK);
		buttonAddActionCancel = (Button) findViewById(R.id.buttonBuildAddActionCancel);

		buttonAddActionOK.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// Send stuff back to previous activity
				Bundle bundle = new Bundle();
				bundle.putInt("Action", BuildAdd.this.getCurrentActionID());
				bundle.putInt("Minutes", BuildAdd.this.getCurrentMinutes());
				bundle.putInt("Seconds", BuildAdd.this.getCurrentSeconds());
				Intent closeIntent = new Intent();
				closeIntent.putExtras(bundle);
				setResult(RESULT_OK, closeIntent);

				BuildAdd.this.finish();
			}
		});

		buttonAddActionCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// Send cancel back to previous activity
				Intent closeIntent = new Intent();
				setResult(RESULT_CANCELED, closeIntent);

				BuildAdd.this.finish();
			}
		});
	}

	public void setDefaultTimeAndRules() {
		editTextTimeMinutes = (EditText) findViewById(R.id.editTextTimeMinutes);
		editTextTimeSeconds = (EditText) findViewById(R.id.editTextTimeSeconds);

		editTextTimeMinutes.setText("");
		editTextTimeSeconds.setText("");

		this.currentMinutes = 0;
		this.currentSeconds = 0;

		editTextTimeMinutes.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable editTextArgument) {
				// TODO Auto-generated method stub
				try {
					int minutes = Integer.parseInt(editTextArgument.toString());
					// Less than 60
					if (minutes > 59) {
						minutes = 59;
					}

					if (minutes < 0) {
						minutes = 0;
					}

					BuildAdd.this.setMinutes(minutes);
				} catch (Exception e) {

				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

		});

		editTextTimeSeconds.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable editTextArgument) {
				// TODO Auto-generated method stub

				try {
					int seconds = Integer.parseInt(editTextArgument.toString());
					// Less than 60
					if (seconds > 59) {
						seconds = 59;
					}

					if (seconds < 0) {
						seconds = 0;
					}

					BuildAdd.this.setSeconds(seconds);
				} catch (Exception e) {

				}

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

		});

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_build_add);

		// Populate an array with all actions
		actionArray = Action.PopulateActions();

		// Set Default Spinners
		SetupSpinnersAndDefaultSpinnerValues();

		// Set Listeners and default values
		setUpListenersAndVariables();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_build_add, menu);
		return true;
	}

}
