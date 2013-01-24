package com.yarakyo.cadebuildorders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class RunBuild extends Activity implements TextToSpeech.OnInitListener {

	public enum AlertOption {
		TTS, Alert
	}

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
	CheckBox checkSoundAlerts;
	CheckBox checkTTSAlerts;
	TextView textViewRunBuildName;

	// Run variables
	int currentRunTime;
	boolean runState; // True is running
	boolean pauseState; // True is paused
	String runBuildName;
	List<Action> runActionList;
	List<RunElement> runElementList;
	Context Context;
	boolean runExited = false;	//Set to true when user presses backspace or back button

	// Handlers
	Handler runTimerHandler;
	Handler runElementHandler;

	// Sound Player
	SoundPool sp;

	// Text to Speech and Alerts
	TextToSpeech TTS;
	boolean TTSenabled = false;
	Boolean alertTTSOn = true;
	Boolean alertSoundsOn = true;
	int alertSoundId;
	AlertOption alertOption = AlertOption.TTS;

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

	public Context getContext() {
		return this.Context;
	}
	
	public boolean getRunExited()
	{
		return this.runExited;
	}

	public List<RunElement> getRunElementList() {
		return this.runElementList;
	}

	public LinearLayout getRunElementLinearLayout() {
		return this.SVRunLLayout;
	}
	
	public boolean getAlertSoundOn() {
		return this.alertSoundsOn;
	}

	public boolean getAlertTTSOn() {
		return this.alertTTSOn;
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
	
	public void setAlertSoundOn(Boolean value)
	{
		this.alertSoundsOn = value;
	}
	
	public void setAlertTTSOn(Boolean value)
	{
		this.alertTTSOn = value;
	}
	

	public void setCurrentTime(int newTime) {
		this.currentRunTime = newTime;
	}

	private void resetTimer() {
		this.currentRunTime = 10;
	}
	
	public void setRunExited(boolean value)
	{
		this.runExited = value;
	}

	public void setRunState(boolean runState) {
		this.runState = runState;
	}

	public void setPauseState(boolean pauseState) {
		this.pauseState = pauseState;
	}

	public void toggleSounds(AlertOption alertOption) {
		if (alertOption == AlertOption.Alert) {
			if (getAlertSoundOn() == true) {
				setAlertSoundOn(false);
			} else {
				setAlertSoundOn(true);
			}

		}

		if (alertOption == AlertOption.TTS) {
			if (getAlertTTSOn() == true) {
				setAlertTTSOn(false);
			} else {
				setAlertTTSOn(true);
			}
		}

	}



	
	//Methods
	private void checkIfExpiredAndPlayAlert(RunElement tempRunElement) {
		if (tempRunElement.checkExpiredandPlaySound() == true) {
			if (getAlertSoundOn() == true)
				sp.play(alertSoundId, 1f, 1f, 1, 0, 1f);
			if (getAlertTTSOn() == true) {
				String toSay = tempRunElement.getAction().actionDescription;
				TTS.speak(toSay, TextToSpeech.QUEUE_ADD, null);
			}

		}
	}

	private void setUpUIHandlers() {
		runTimerHandler = new Handler() {
			public void handleMessage(Message msg) {
				int currentRunTime = getCurrentRunTime();
				RunBuild.this.setRunTimer(currentRunTime);
			}
		};
		
		runElementHandler = new Handler() {
			// running Handler
			public void handleMessage(Message msg) {
				List<RunElement> tempRunElementList = getRunElementList();
				Iterator<RunElement> tempRELIterator = tempRunElementList
						.iterator();
				while (tempRELIterator.hasNext()) {
					RunElement tempRunElement = tempRELIterator.next();
					tempRunElement.setProgressBarTime(getCurrentRunTime());

					// Set red progress bar if expired
					tempRunElement.testTimeExpired(getCurrentRunTime());

					// If expired then play sound
					checkIfExpiredAndPlayAlert(tempRunElement);

					// Remove after 5 seconds of being expired
					if (tempRunElement.testForRemovalTime(getCurrentRunTime()) == true) {
						// Clear all children from Views
						SVRunLLayout.removeView(tempRunElement
								.getElementcontainerLayout());
					}
				}
			}

		};

	}

	private void resetRunElements() {
		List<RunElement> tempRunElementList = getRunElementList();
		for (RunElement runElement : tempRunElementList) {
			runElement.resetProgressBar();
			runElement.resetSoundPlayed();
		}
	}

	private void setButtonLisenters() {
		buttonRunStart = (Button) findViewById(R.id.buttonRunStart);
		buttonRunStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
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
				setPauseState(true);
			}
		});

		buttonRunReset = (Button) findViewById(R.id.buttonRunReset);
		buttonRunReset.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Reset conditions
				setPauseState(true);
				resetTimer();
				resetRunElements();
				resetDisplayRunElements();
				displayRunElements();
				runTimerHandler.sendEmptyMessage(0);
				runElementHandler.sendEmptyMessage(0);
			}
		});

		buttonRunBack = (Button) findViewById(R.id.buttonRunBack);
		buttonRunBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				setRunExited(true);
				shutdownProcedure();
				RunBuild.this.finish();
			}
		});

		// Check box for sounds
		checkSoundAlerts = (CheckBox) findViewById(R.id.checkSoundAlerts);
		checkSoundAlerts.setChecked(true);
		checkSoundAlerts
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						toggleSounds(AlertOption.Alert);
					}
				});

		checkTTSAlerts = (CheckBox) findViewById(R.id.checkTTSAlerts);
		checkTTSAlerts.setChecked(true);
		checkTTSAlerts
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						toggleSounds(AlertOption.TTS);
					}
				});

	}

	private LinearLayout packRunElementIntoContainer(RunElement tempRunElement) {
		// Container
		LinearLayout ElementContainerLayout = new LinearLayout(this.Context);
		ElementContainerLayout.setOrientation(LinearLayout.HORIZONTAL);

		// Left Layouts
		LinearLayout ElementLeftLayout = new LinearLayout(Context);
		ElementLeftLayout.setOrientation(LinearLayout.VERTICAL);
		ImageView runElementImage = new ImageView(Context);
		String imagePointer = "icon_";
		imagePointer += String.valueOf(tempRunElement.getAction().actionID);
		int id = getResources().getIdentifier(imagePointer, "drawable",
				getPackageName());
		runElementImage.setImageResource(id);

		ElementLeftLayout.addView(runElementImage);

		// Right Layout
		LinearLayout ElementRightLayout = new LinearLayout(Context);
		ElementRightLayout.setOrientation(LinearLayout.VERTICAL);
		ElementRightLayout.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		ElementRightLayout.addView(tempRunElement
				.getTextViewRunActionDescription());
		ElementRightLayout.addView(tempRunElement.getTextViewRunActionTime());
		ElementRightLayout.addView(tempRunElement.getProgressbar());

		ElementContainerLayout.addView(ElementLeftLayout);
		ElementContainerLayout.addView(ElementRightLayout);

		// Add references to runElement object
		tempRunElement.setContainerLayout(ElementContainerLayout,
				ElementLeftLayout, ElementRightLayout);
		tempRunElement.setImageView(runElementImage);
		return ElementContainerLayout;
	}

	private void resetDisplayRunElements() {
		SVRunLLayout.removeAllViews();
		Iterator<RunElement> runElementListIterator = runElementList.iterator();
		while (runElementListIterator.hasNext()) {
			RunElement tempRunElement = runElementListIterator.next();
			LinearLayout tempLeftLayout = tempRunElement.getElementLeftLayout();
			tempLeftLayout.removeAllViews();

			LinearLayout tempRightLayout = tempRunElement
					.getElementRightLayout();
			tempRightLayout.removeAllViews();

			LinearLayout tempElementContainerLayout = tempRunElement
					.getElementcontainerLayout();
			tempElementContainerLayout.removeAllViews();
		}
	}

	private void displayRunElements() {
		SVRunLLayout.removeAllViews();
		Iterator<RunElement> runElementListIterator = runElementList.iterator();
		while (runElementListIterator.hasNext()) {
			RunElement tempRunElement = runElementListIterator.next();
			LinearLayout ElementContainerLayout = packRunElementIntoContainer(tempRunElement);
			SVRunLLayout.addView(ElementContainerLayout);
		}
	}

	private void setUpRunElements() {
		Intent runIntent = getIntent();
		ActionList passedBuild = (ActionList) runIntent
				.getSerializableExtra("RunBuild");

		// Set Build Name
		this.runBuildName = passedBuild.getBuildName();
		textViewRunBuildName.setText(runBuildName);

		// Set up Local Variables
		this.runElementList = new ArrayList<RunElement>();
		this.runActionList = new ArrayList<Action>();
		this.Context = this;
		runActionList = passedBuild.getActionList();

		Iterator<Action> runActionListIterator = this.runActionList.iterator();

		while (runActionListIterator.hasNext()) {
			RunElement tempRunElement = new RunElement(
					runActionListIterator.next(), this);
			runElementList.add(tempRunElement);
		}

		// Set up Thread to update elements

		displayRunElements();
		runTimerHandler.sendEmptyMessage(0);
		runElementHandler.sendEmptyMessage(0);
	}

	private void setUpDisplayVaraibles() {
		runTimer = (TextView) findViewById(R.id.textViewRunTimer);
		textViewRunBuildName = (TextView) findViewById(R.id.textViewRunBuildName);
		scrollViewRun = (ScrollView) findViewById(R.id.scrollViewRun);
		SVRunLLayout = (LinearLayout) findViewById(R.id.SVRunLLayout);
	}

	private void setUpSoundPool() {
		sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 100);
		alertSoundId = sp.load(this, R.raw.actionalert, 1);
	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			int result = TTS.setLanguage(Locale.ENGLISH);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				TTSenabled = false;
			} else {
				TTSenabled = true;
			}
		}
	}

	private void setUpTTS() {
		TTS = new TextToSpeech(this, this);		
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

		// Set up Sound Pool
		setUpSoundPool();

		// Set up TTS
		setUpTTS();
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
				while (this.isInterrupted() == false) {
					if(getRunExited() == true) return;
					try {
						if (getPauseState() == false && getRunState() == true && getRunExited() == false) {
							runTimerHandler.sendEmptyMessage(0);
							runElementHandler.sendEmptyMessage(0);
							setCurrentTime(getCurrentRunTime() + 1);
							sleep(710);
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

	private void tearDownSoundPool() {
		sp.unload(alertSoundId);
		sp.release();
	}

	private void shutdownProcedure()
	{
		runThread.interrupt();		
		// Shut Down TTS
		if (TTS != null) {
			TTS.stop();
			TTS.shutdown();
		}
		// Unload all sounds
		tearDownSoundPool();
		finish();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		setRunExited(true);
		shutdownProcedure();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();		
	}

}
