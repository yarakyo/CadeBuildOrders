package com.yarakyo.cadebuildorders;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BuildAdapter extends ArrayAdapter<String> {
	Context context;
	List<String> stringActionList;
	List<Action> allActionList;
 	
	public BuildAdapter(Context context, int textViewResourceId,
			List<String> stringActionList,List<Action> allActionList) {
		super(context, textViewResourceId, stringActionList);
		this.context = context;
		this.stringActionList = new ArrayList<String>();
		this.stringActionList = stringActionList;
		this.allActionList = new ArrayList<Action>();
		this.allActionList = allActionList;
	}
	

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE );
		View row=inflater.inflate(R.layout.spinner_row, parent, false);
		TextView label = (TextView) row.findViewById(R.id.row_name);
		String actionName = this.stringActionList.get(position);
		label.setText(actionName);
		label.setTextColor(context.getResources().getColor(R.color.black));
		label.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f);
		Action tempAction = Action.getActionByName(this.allActionList,actionName);
		ImageView icon = (ImageView) row.findViewById(R.id.row_icon);
		String imagePointer = "icon_";
		imagePointer += String.valueOf(tempAction.getActionID());
		int id = context.getResources().getIdentifier(imagePointer, "drawable",
				context.getPackageName());
		icon.setImageResource(id);
		
		return row;
	}
}
