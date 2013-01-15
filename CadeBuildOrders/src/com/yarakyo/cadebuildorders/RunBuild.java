package com.yarakyo.cadebuildorders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class RunBuild extends Activity {

	// UI Variables
	RadioGroup radioGroupRun;
	ScrollView scrollViewRun;
	LinearLayout SVRunLLayout;
	TextView runTimer;
	Thread runThread;
	Button buttonRunStart;
	Button buttonRunPause;
	Button buttonRunReset;
	Button buttonRunBack;

	// Run variables
	int currentRunTime;
	boolean runState; // True is running
	boolean pauseState; // True is paused
	String runBuildName;
	List<Action> runActionList;
	List<RunElement> runElementList;

	// Handlers
	Handler runTimerHandler;
	Handler runElementHandler;

	// Getter
	public TextView getRunTimer() {
		return this.runTimer;
	}

	public int getCurrentRunTime() {
		return this.currentRunTime;
	}

	public boolean getRunState() {
		return this.runState;
	}

	public boolean getPauseState() {
		return this.pauseState;
	}

	public List<RunElement> getRunElementList() {
		return this.runElementList;
	}

	public LinearLayout getRunElementLinearLayout() {
		return this.SVRunLLayout;
	}

	// Setters
	public void setRunTimer(int time) {

		// Format timer
		int minutes = time / 60;
		int seconds = time % 60;
		String formattedTime;
		if (seconds < 10) {
			formattedTime = minutes + ":0" + seconds;
		} else {
			formattedTime = minutes + ":" + seconds;
		}

		// Set timer
		runTimer = (TextView) findViewById(R.id.textViewRunTimer);
		runTimer.setText(formattedTime);
	}

	public void setCurrentTime(int newTime) {
		this.currentRunTime = newTime;
	}

	public void setRunState(boolean runState) {
		this.runState = runState;
	}

	public void setPauseState(boolean pauseState) {
		this.pauseState = pauseState;
	}

	private void setUpUIHandlers() {
		// TODO Auto-generated method stub
		runTimerHandler = new Handler() {
			public void handleMessage(Message msg) {
				int currentRunTime = getCurrentRunTime();
				RunBuild.this.setRunTimer(currentRunTime);
			}
		};

		runElementHandler = new Handler() {
			public void handleMessage(Message msg) {
				List<RunElement> tempRunElementList = getRunElementList();
				Iterator<RunElement> tempRELIterator = tempRunElementList
						.iterator();
				while (tempRELIterator.hasNext()) {
					RunElement tempRunElement = tempRELIterator.next();
					tempRunElement.setProgressBarTime(getCurrentRunTime());
					tempRunElement.testTimeExpired(getCurrentRunTime());
					if (tempRunElement.testForRemovalTime(getCurrentRunTime()) == true) {
						LinearLayout tempLinearLayout = getRunElementLinearLayout();
						tempLinearLayout.removeView(tempRunElement
								.getActionDescriptionAndTime());
						tempLinearLayout.removeView(tempRunElement
								.getProgressbar());
					}
				}
			}

		};

	}

	private void resetTimer() {
		this.currentRunTime = 0;
	}

	private void resetRunElements()
	{
		List<RunElement> tempRunElementList = getRunElementList();
		for(RunElement runElement: tempRunElementList)
		{
			runElement.resetProgressBar();
		}
	}

	private void setButtonLisenters() {
		buttonRunStart = (Button) findViewById(R.id.buttonRunStart);
		buttonRunStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (getRunState() == false) {
					runThread.start();
					setRunState(true);
				}

				if (getRunState() == true && getPauseState() == true) {
					setPauseState(false);
				}
			}
		});
		buttonRunPause = (Button) findViewById(R.id.buttonRunPause);
		buttonRunPause.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setPauseState(true);
			}
		});

		buttonRunReset = (Button) findViewById(R.id.buttonRunReset);
		buttonRunReset.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// Reset conditions
				setPauseState(true);
				setCurrentTime(0);
				runTimerHandler.sendEmptyMessage(0);
				runElementHandler.sendEmptyMessage(0);
				resetRunElements();
				displayRunElements();
			}
		});

		buttonRunBack = (Button) findViewById(R.id.buttonRunBack);
		buttonRunBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				RunBuild.this.finish();
			}
		});
	}

	private void displayRunElements() {
		SVRunLLayout.removeAllViews();
		Iterator<RunElement> runElementListIterator = runElementList.iterator();
		while (runElementListIterator.hasNext()) {
			RunElement tempRunElement = runElementListIterator.next();
			SVRunLLayout.addView(tempRunElement.getActionDescriptionAndTime());
			SVRunLLayout.addView(tempRunElement.getProgressbar());
		}

	}

	private void setUpRunElements() {
		Intent runIntent = getIntent();
		ActionList passedBuild = (ActionList) runIntent
				.getSerializableExtra("RunBuild");
		this.runBuildName = passedBuild.getBuildName();

		// Set up Local Variables
		this.runElementList = new ArrayList<RunElement>();
		this.runActionList = new ArrayList<Action>();
		runActionList = passedBuild.getActionList();

		Iterator<Action> runActionListIterator = this.runActionList.iterator();

		while (runActionListIterator.hasNext()) {
			RunElement tempRunElement = new RunElement(
					runActionListIterator.next(), this);
			runElementList.add(tempRunElement);
		}

		// Set up Thread to update elements

		displayRunElements();

	}

	private void setUpDisplayVaraibles() {
		runTimer = (TextView) findViewById(R.id.textViewRunTimer);
		scrollViewRun = (ScrollView) findViewById(R.id.scrollViewRun);
		SVRunLLayout = (LinearLayout) findViewById(R.id.SVRunLLayout);
	}

	private void setUpListenersAndVariables() {

		// Set Default time
		resetTimer();

		// Set up Handlers
		setUpUIHandlers();

		// Set up Display Variables
		setUpDisplayVaraibles();

		// Set Buttons
		setButtonLisenters();

		// Set run Timer Thread
		setUpRunTimer();

		// Set up Run Elements
		setUpRunElements();

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_run_build);

		// Stop screen for sleeping
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setUpListenersAndVariables();

	}

	private void setUpRunTimer() {
		// Set up Thread
		runThread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						if (getPauseState() == false && getRunState() == true) {
							runTimerHandler.sendEmptyMessage(0);
							runElementHandler.sendEmptyMessage(0);
							setCurrentTime(getCurrentRunTime() + 1);
							sleep(700);
						}
					} catch (Exception e) {

					}
				}
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_run_build, menu);
		return true;
	}
}