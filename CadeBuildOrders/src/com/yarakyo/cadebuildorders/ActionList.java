package com.yarakyo.cadebuildorders;

import java.io.Serializable;
import java.util.List;

public class ActionList implements Serializable {

	public enum Category {
		Building, Unit, Action
	}

	public enum Race {
		Terran, Protoss, Zerg
	}

	List<Action> actionList;
	String buildName;
	Race vsRace;

	public ActionList(List<Action> actionList, String buildName, Race vsRace) {
		this.actionList = actionList;
		this.buildName = buildName;
		this.vsRace = vsRace;
	}

	public ActionList() {
		this.buildName = "";
	}

	public ActionList(String buildName, Race vsRace) {
		this.buildName = buildName;
		this.vsRace = vsRace;
	}

	// Getters
	public String getBuildName() {
		return this.buildName;
	}

	public List<Action> getActionList() {
		return this.actionList;
	}

	public Race getRace() {
		return this.vsRace;
	}

	// Setters

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}

}
