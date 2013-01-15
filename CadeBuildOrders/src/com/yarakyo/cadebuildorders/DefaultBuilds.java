package com.yarakyo.cadebuildorders;

import java.util.ArrayList;
import java.util.List;

import com.yarakyo.cadebuildorders.ActionList.Race;

public class DefaultBuilds {
	static List<Action> allActions;
	static List<ActionList> defaultBuilds = new ArrayList<ActionList>();

	private static void terranBuilds() {
		ActionList tempBuild = new ActionList(
				"Marine Hellion Banshee Pressure", Race.Terran);
		String[] actions = { "Supply Depot", "Refinery", "Barracks", "Factory",
				"Orbital Command", "Tech Lab", "Supply Depot", "Starport" };
		int[] minutes = { 1, 1, 1, 3, 3, 3, 3, 4 };
		int[] seconds = { 0, 20, 57, 4, 20, 35, 40, 7 };

		List<Action> tempActionList = new ArrayList<Action>();
		for (int i = 0; i < actions.length; i++) {
			Action tempAction = new Action(Action.getActionByName(allActions,
					actions[i]), minutes[i], seconds[i]);
			tempActionList.add(tempAction);
		}

		tempBuild.setActionList(tempActionList);

		defaultBuilds.add(tempBuild);
	}

	private static void zergBuilds() {

		ActionList tempBuild = new ActionList("3 CC Dual port Banshee",
				Race.Zerg);
		String[] actions = { "Supply Depot", "Command Center", "Barracks",
				"Refinery", "Orbital Command", "Orbital Command",
				"Command Center", "Refinery", "Factory", "Barracks",
				"Engineering Bay", "Bunker", "Hellion", "Starport", "Starport",
				"Orbital Command", "Supply Depot", "Tech Lab", "Tech Lab" };
		int[] minutes = { 0, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7,
				7 };
		int[] seconds = { 58, 26, 50, 3, 56, 9, 43, 54, 19, 20, 34, 50, 26, 30,
				30, 39, 55, 23, 23 };

		List<Action> tempActionList = new ArrayList<Action>();
		for (int i = 0; i < actions.length; i++) {
			Action tempAction = new Action(Action.getActionByName(allActions,
					actions[i]), minutes[i], seconds[i]);
			tempActionList.add(tempAction);
		}

		tempBuild.setActionList(tempActionList);

		defaultBuilds.add(tempBuild);
	}

	private static void protossBuilds() {
		ActionList tempBuild = new ActionList("Command Center first",
				Race.Protoss);
		String[] actions = { "Supply Depot", "Command Center", "Barracks",
				"Barracks", "Orbital Command", "Marine", "Orbital Command",
				"Bunker", "Barracks", "Barracks", "Refinery", "Refinery",
				"Supply Depot" };
		int[] minutes = { 0, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5, 5, 5 };
		int[] seconds = { 52 ,28, 52, 24, 58, 59,11, 18, 9, 12, 38, 39, 40  };

		List<Action> tempActionList = new ArrayList<Action>();
		for (int i = 0; i < actions.length; i++) {
			Action tempAction = new Action(Action.getActionByName(allActions,
					actions[i]), minutes[i], seconds[i]);
			tempActionList.add(tempAction);
		}

		tempBuild.setActionList(tempActionList);

		defaultBuilds.add(tempBuild);
	}

	public static List<ActionList> populateDefaultBuilds(List<Action> allActionArgs) {
		allActions = allActionArgs;
		terranBuilds();
		protossBuilds();
		zergBuilds();
		return defaultBuilds;
	}
}
