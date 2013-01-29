package com.yarakyo.cadebuildorders;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BuildMenuHandler {
	LinearLayout MenuLayout;
	List<RadioGroup> radioGroupList;
	List<Action> actionList;
	int checkedRadioButton;
	Context context;
	int radioButtonCounter;

	// Getters
	public LinearLayout getMenu() {
		return this.MenuLayout;
	}

	public int getCheckedRadioButton() {
		return this.checkedRadioButton;
	}

	private void clearOtherButtonsAndSetSelected(int buttonId) {
		for (RadioGroup eachRadioGroup : radioGroupList) {
			eachRadioGroup.clearCheck();
			eachRadioGroup.check(buttonId);
		}
		checkedRadioButton = buttonId;
	}

	private LinearLayout packActionIntoDisplayableLayout(Action tempRadioAction) {
		LinearLayout mainContainer = new LinearLayout(context);
		mainContainer.setOrientation(LinearLayout.HORIZONTAL);

		LinearLayout imageLayout = new LinearLayout(context);
		imageLayout.setOrientation(LinearLayout.HORIZONTAL);

		LinearLayout textLayout = new LinearLayout(context);
		textLayout.setOrientation(LinearLayout.VERTICAL);

		// Left side
		RadioButton tempRadioButton = new RadioButton(context);
		RadioGroup tempRadioGroup = new RadioGroup(context);

		// Set id for radio button and increment counter
		tempRadioButton.setId(radioButtonCounter);
		radioButtonCounter++;

		// Add listener to clear other ones when clicked
		tempRadioButton.setTag(tempRadioButton);
		tempRadioButton.setOnClickListener(new RadioButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RadioButton tempRadioButton = (RadioButton) v.getTag();
				clearOtherButtonsAndSetSelected(tempRadioButton.getId());
			}
		});

		tempRadioGroup.addView(tempRadioButton);
		radioGroupList.add(tempRadioGroup);
		imageLayout.addView(tempRadioGroup);

		ImageView runElementImage = new ImageView(context);
		String imagePointer = "icon_";
		imagePointer += String.valueOf(tempRadioAction.getActionID());
		int id = context.getResources().getIdentifier(imagePointer, "drawable",
				context.getPackageName());
		runElementImage.setImageResource(id);
		imageLayout.addView(runElementImage);

		// Right side
		TextView actionName = new TextView(context);
		actionName.setText(tempRadioAction.getActionDescription());
		actionName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f);

		TextView actionTime = new TextView(context);

		int supply = tempRadioAction.getSupply();
		if (supply != 0) {
			// Draw with supply
			actionTime.setText(tempRadioAction.getMinutes() + " m "
					+ tempRadioAction.getSeconds() + "s  - Supply: "
					+ tempRadioAction.getSupply());
		} else {
			// Draw without supply
			actionTime.setText(tempRadioAction.getMinutes() + " m "
					+ tempRadioAction.getSeconds() + "s.");
		}

		actionTime.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f);

		textLayout.addView(actionName);
		textLayout.addView(actionTime);

		mainContainer.addView(imageLayout);
		mainContainer.addView(textLayout);
		return mainContainer;
	}

	public BuildMenuHandler(List<Action> actionList, Context context) {
		this.context = context;
		this.radioGroupList = new ArrayList<RadioGroup>();
		this.actionList = actionList;
		this.radioButtonCounter = 0;
		this.MenuLayout = new LinearLayout(context);
		MenuLayout.setOrientation(LinearLayout.VERTICAL);

		for (Action eachAction : actionList) {
			LinearLayout packedLayout = packActionIntoDisplayableLayout(eachAction);
			MenuLayout.addView(packedLayout);
		}

	}

}
