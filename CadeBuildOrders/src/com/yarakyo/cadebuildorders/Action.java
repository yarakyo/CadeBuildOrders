package com.yarakyo.cadebuildorders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yarakyo.cadebuildorders.ActionList.Category;
import com.yarakyo.cadebuildorders.ActionList.Race;

public class Action implements Serializable {

	// Variables
	private static final long serialVersionUID = 1L;
	Race race;
	Category category;
	int actionID;
	String actionDescription;

	// Timings
	int minutes;
	int seconds;

	// Getters
	public String getRace() {
		return this.race.toString();
	}

	public String getActionDescription() {
		return this.actionDescription;
	}

	public int getActionID() {
		return this.actionID;
	}

	public int getMinutes() {
		return this.minutes;
	}

	public int getSeconds() {
		return this.seconds;
	}

	// Setters
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public static Action getActionByName(List<Action> actionArray, String name) {
		Action returnAction = new Action();
		Iterator<Action> actionArrayIterator = actionArray.iterator();
		while (actionArrayIterator.hasNext()) {
			Action Temp = actionArrayIterator.next();
			if (Temp.actionDescription.equals(name)) {
				returnAction = Temp;
				break;
			}
		}
		return returnAction;
	}

	public static Action getActionByID(List<Action> actionArray, int ActionID) {
		Action returnAction = new Action();
		Iterator<Action> actionArrayIterator = actionArray.iterator();
		while (actionArrayIterator.hasNext()) {
			Action Temp = actionArrayIterator.next();
			if (Temp.actionID == ActionID) {
				returnAction = Temp;
				break;
			}
		}
		return returnAction;
	}
	
	public static List<Integer> applyFilterAndReturnIds(Race Race, Category Category,
			List<Action> actionArray) {
		List<Integer> filteredActions = new ArrayList<Integer>();
		Iterator<Action> actionArrayIterator = actionArray.iterator();
		while (actionArrayIterator.hasNext()) {
			Action actionTemp = actionArrayIterator.next();
			if (actionTemp.race.equals(Race)
					&& actionTemp.category.equals(Category)) {
				filteredActions.add(actionTemp.getActionID());
			}
		}
		return filteredActions;
	}

	// Default Constructor
	public Action() {
		this.race = Race.Terran;
		this.category = Category.Building;
		this.actionID = -1;
		this.actionDescription = "Empty";
		this.minutes = 0;
		this.seconds = 0;
	}

	// Constructor at BuildAdd
	public Action(int actionID, Race race, Category catagory,
			String actionDescription) {
		this.race = race;
		this.category = catagory;
		this.actionID = actionID;
		this.actionDescription = actionDescription;
	}

	// Conversion Constructor used to create Action with Time
	public Action(Action action, int minutes, int seconds) {
		this.race = action.race;
		this.category = action.category;
		this.actionID = action.actionID;
		this.actionDescription = action.actionDescription;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public static List<Action> PopulateActions() {
		List<Action> ActionArray = new ArrayList<Action>();
		int arrayCounter = 1;

		// Terran Section
		// Buildings
		String[] terranBuildings = { "Supply Depot", "Command Center",
				"Orbital Command", "Planetary Fortress", "Refinery",
				"Barracks", "Engineering Bay", "Sensor Tower",
				"Missile Turret", "Factory", "Ghost Academy", "Bunker",
				"Starport", "Armory", "Fusion Core" ,"Tech Lab","Reactor"};
		for (int i = 0; i < terranBuildings.length; i++) {
			ActionArray.add(new Action(arrayCounter, Race.Terran,
					Category.Building, terranBuildings[i]));
			arrayCounter++;
		}

		// Units
		String[] terranUnits = { "SCV", "Marine", "Marauder", "Reaper",
				"Ghost", "Hellion", "Siege Tank", "Thor", "Viking", "Medivac",
				"Raven", "Banshee", "Battlecruiser", "Point Defense Drone",
				"Auto-Turret" };
		for (int i = 0; i < terranUnits.length; i++) {
			ActionArray.add(new Action(arrayCounter, Race.Terran,
					Category.Unit, terranUnits[i]));
			arrayCounter++;
		}

		// Actions

		String[] terranActions = { "Calldown Mule", "Scanner Sweep",
				"Calldown Extra Supplies", "Lift", "Land", "Salvage",
				"Infantry Weapons Level 1", "Infantry Weapons Level 2",
				"Infantry Weapons Level 3", "Vehicle Weapons Level 1",
				"Vehicle Weapons Level 2", "Vehicle Weapons Level 3",
				"Ship Weapons Level 1", "Ship Weapons Level 2",
				"Ship Weapons Level 3", "Infantry Armor Level 1",
				"Infantry Armor Level 2", "Infantry Armor Level 3",
				"Vehicle Plating Level 1", "Vehicle Plating Level 2",
				"Vehicle Plating Level 3", "Nitro Packs",
				"Hi-Sec Auto Tracking", "250mm Strike Cannons",
				"Cloaking Field", "Concussive Shells", "Personal Cloaking",
				"Siege Tech", "Stimpack", "Weapon Refit", "Behemoth Reactor",
				"Caduceus Reactor", "Corvid Reactor", "Moebius Reactor",
				"Building Armor", "Combat Shield", "Durable Materials",
				"Infernal Pre-Igniter", "Neosteel Frame", "Arm Silo with Nuke",
				"Lower Depot", "Raise Depot" };

		for (int i = 0; i < terranActions.length; i++) {
			ActionArray.add(new Action(arrayCounter, Race.Terran,
					Category.Action, terranActions[i]));
			arrayCounter++;
		}

		// Protoss Section
		// Buildings
		String[] protossBuildings = { "Nexus", "Assimilator", "Pylon",
				"Gateway", "Forge", "Cybernetics Core", "Photon Cannon",
				"Stargate", "Robotics Facility", "Twlight Council",
				"Fleet Beacon", "Robotics Bay", "Templar Archives",
				"Dark Shrine" };
		for (int i = 0; i < protossBuildings.length; i++) {
			ActionArray.add(new Action(arrayCounter, Race.Protoss,
					Category.Building, protossBuildings[i]));
			arrayCounter++;
		}

		// Units
		String[] protossUnits = { "Probe", "Zealot", "Sentry", "Stalker",
				"Dark Templar", "High Templar", "Archon", "Observer",
				"Warp Prism", "Immortal", "Colossus", "Phoenix", "Void Ray",
				"Carrier", "Interceptor", "Mothership" };
		for (int i = 0; i < protossUnits.length; i++) {
			ActionArray.add(new Action(arrayCounter, Race.Protoss,
					Category.Unit, protossUnits[i]));
			arrayCounter++;
		}

		// Actions
		String[] protossActions = { "Chrono Boost", "Ground Weapons Level 1",
				"Ground Weapons Level 2", "Ground Weapons Level 3",
				"Air Weapons Level 1", "Air Weapons Level 2",
				"Air Weapons Level 3", "Ground Armor Level 1",
				"Ground Armor Level 2", "Ground Armor Level 3",
				"Air Armor Level 1", "Air Armor Level 2", "Air Armor Level 3",
				"Shields Level 1", "Shields Level 2", "Shields Level 3",
				"Charge", "Gravitic Boosters", "Gravitic Drive",
				"Anion Pulse-Crystals", "Extended Thermal Lance",
				"Psionic Storm", "Hallucination", "Blink", "Graviton Catapult" };

		for (int i = 0; i < protossActions.length; i++) {
			ActionArray.add(new Action(arrayCounter, Race.Protoss,
					Category.Action, protossActions[i]));
			arrayCounter++;
		}

		// Zerg Section
		// Buildings
		String[] zergBuildings = { "Hatchery", "Extractor", "Spawning Pool",
				"Evolution Chamer", "Spine Crawler", "Spore Crawler",
				"Roach Warren", "Baneling Nest", "Lair","Infestation Pit",
				"Hydralisk Den", "Spire", "Nydus Network", "Hive",
				"Ultralisk Cavern", "Greater Spire" };
		for (int i = 0; i < zergBuildings.length; i++) {
			ActionArray.add(new Action(arrayCounter, Race.Zerg,
					Category.Building, zergBuildings[i]));
			arrayCounter++;
		}

		// Units
		String[] zergUnits = { "Drone", "Zergling", "Baneling", "Roach",
				"Hydralisk", "Mutalisk", "Corruptor", "Infestor", "Ultralisk",
				"Brood Lord", "Overlord", "Overseer", "Queen",
				"Changeling", "Infested Terran" };
		for (int i = 0; i < zergUnits.length; i++) {
			ActionArray.add(new Action(arrayCounter, Race.Zerg, Category.Unit,
					zergUnits[i]));
			arrayCounter++;
		}

		// Actions
		String[] zergActions = { "Inject Larva", "Creep Tumor", "Transfuse",
				"Melee Attacks Level 1", "Melee Attacks Level 2",
				"Melee Attacks Level 3", "Missile Attacks Level 1",
				"Missile Attacks Level 2", "Missile Attacks Level 3",
				"Flyer Attacks Level 1", "Flyer Attacks Level 2",
				"Flyer Attacks Level 3", "Ground Carapace Level 1",
				"Ground Carapace Level 2", "Ground Carapace Level 3",
				"Flyer Carapace Level 1", "Flyer Carapace Level 2",
				"Flyer Carapace Level 3", "Chitinous Plating",
				"Centrifugal Hooks", "Glial Reconstitution", "Metabolic Boost",
				"Pneumatized Carapace", "Grooved Spines", "Burrow",
				"Neural Parasite", "Pathogen Glands", "Adrenal Glands",
				"Tunneling Claws", "Ventral Sacs"};

		for (int i = 0; i < zergActions.length; i++) {
			ActionArray.add(new Action(arrayCounter, Race.Zerg, Category.Action,
					zergActions[i]));
			arrayCounter++;
		}
		
		return ActionArray;
	}
}
