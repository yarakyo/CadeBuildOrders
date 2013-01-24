package com.yarakyo.cadebuildorders;

import java.util.ArrayList;
import java.util.List;

import com.yarakyo.cadebuildorders.ActionList.Race;

public class DefaultBuilds {
	static List<Action> allActions;
	static List<ActionList> defaultBuilds = new ArrayList<ActionList>();

	private static void packBuildAndPutIntoDefaultBuilds(ActionList tempBuild,
			String[] actions, int[] minutes, int[] seconds) {
		List<Action> tempActionList = new ArrayList<Action>();
		for (int i = 0; i < actions.length; i++) {
			Action tempAction = new Action(Action.getActionByName(allActions,
					actions[i]), minutes[i], seconds[i]);
			tempActionList.add(tempAction);
		}
		tempBuild.setActionList(tempActionList);
		defaultBuilds.add(tempBuild);
	}

	private static void terranBuilds() {
		ActionList tempBuild = new ActionList(
				"vT - Marine Hellion Banshee Pressure", Race.Terran);
		String[] actions = { "Supply Depot", "Refinery", "Barracks", "Factory",
				"Orbital Command", "Marine", "Supply Depot", "Tech Lab",
				"Starport", "Marine", "Hellion", "Supply Depot", "Banshee",
				"Supply Depot", "Hellion", "Marine", "Supply Depot", "Hellion",
				"Marine", "Banshee", "Supply Depot", "Hellion", "Marine",
				"Banshee", "Supply Depot", "Hellion", "Marine", "Supply Depot",
				"Hellion", "Marine", "Refinery", "Command Center", "Factory",
				"Factory", "Reactor", "Reactor" };
		int[] minutes = { 1, 1, 1, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5,
				5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 9, 9 };
		int[] seconds = { 0, 20, 55, 5, 10, 10, 40, 40, 20, 25, 25, 30, 5, 10,
				10, 10, 45, 45, 45, 10, 50, 50, 50, 10, 20, 20, 20, 0, 0, 0, 0,
				15, 40, 40, 0, 0 };
		packBuildAndPutIntoDefaultBuilds(tempBuild, actions, minutes, seconds);

		ActionList tempBuild2 = new ActionList("vZ - 3 CC Dual port Banshee",
				Race.Terran);
		String[] actions2 = { "Supply Depot", "Command Center", "Barracks",
				"Refinery", "Orbital Command", "Marine", "Orbital Command",
				"Marine", "Command Center", "Marine", "Refinery", "Factory",
				"Barracks", "Engineering Bay", "Bunker", "Hellion", "Starport",
				"Starport", "Orbital Command", "Supply Depot", "Refinery",
				"Refinery", "Tech Lab", "Tech Lab", "Supply Depot",
				"Supply Depot", "Banshee", "Banshee", "Supply Depot",
				"Factory", "Factory", "Armory", "Supply Depot", "Supply Depot",
				"Marine", "Marine", "Supply Depot", "Supply Depot", "Thor",
				"Thor", "Marine", "Marine", "Vehicle Weapons Level 1",
				"Supply Depot", "Missile Turret", "Missile Turret",
				"Missile Turret", "Supply Depot", "Supply Depot", "Factory",
				"Factory", "Factory", "Siege Tech", "Marine", "Marine",
				"Siege Tank", "Siege Tank", "Armory",
				"Vehicle Weapons Level 2", "Vehicle Plating Level 1" };
		int[] minutes2 = { 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6,
				6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9,
				9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11,
				11, 11, 11, 12, 12 };
		int[] seconds2 = { 0, 25, 50, 5, 55, 55, 10, 20, 30, 45, 55, 20, 25,
				35, 55, 25, 30, 30, 40, 55, 0, 0, 25, 25, 25, 40, 45, 45, 15,
				15, 15, 20, 20, 20, 15, 15, 20, 20, 30, 30, 45, 45, 50, 50, 5,
				5, 5, 20, 20, 50, 50, 50, 50, 15, 15, 25, 25, 30, 50, 50 };

		packBuildAndPutIntoDefaultBuilds(tempBuild2, actions2, minutes2,
				seconds2);

		ActionList tempBuild3 = new ActionList("vP - Command Center first",
				Race.Terran);
		String[] actions3 = {"Supply Depot", "Command Center", "Barracks",
	            "Barracks", "Orbital Command", "Marine", "Bunker", "Orbital Command",
	            "Barracks", "Barracks", "Refinery", "Refinery",
	            "Supply Depot", "Tech Lab", "Engineering Bay", "Stimpack", "Bunker",
	            "Infantry Weapons Level 1", "Factory", "Supply Depot", "Refinery",
	            "Missile Turret", "Starport", "Supply Depot", "Supply Depot", "Supply Depot",
	            "Refinery", "Armory", "Engineering Bay", "Medivac", "Medivac", "Command Center", "Reactor", "Reactor", "Reactor",
	            "Infantry Weapons Level 2", "Infantry Armor Level 1", "Combat Shield", "Barracks", "Barracks", "Starport"};
	        int[] minutes3 = {1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6,
	            6, 7, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10};
	        int[] seconds3 = {0, 20, 45, 20, 55, 0, 0, 10, 0, 0, 10, 10, 20, 50, 0, 20,
	            20, 30, 45, 45, 15, 20, 45, 45, 20, 20, 30, 45, 45, 0, 0, 20, 20, 20,
	            30, 40, 40, 0, 30, 30, 40};
		packBuildAndPutIntoDefaultBuilds(tempBuild3, actions3, minutes3,
				seconds3);

	}

	private static void zergBuilds() {
		ActionList tempBuild = new ActionList("vT - 15 Hatchery", Race.Zerg);
		String[] actions = { "Hatchery", "Spawning Pool", "Queen", "Queen",
				"Zergling", "Queen", "Queen", "Extractor", "Extractor",
				"Hatchery", "Metabolic Boost", "Evolution Chamber",
				"Evolution Chamber", "Roach Warren", "Baneling Nest",
				"Melee Attacks Level 1", "Ground Carapace Level 1", "Lair",
				"Extractor", "Extractor", "Extractor", "Hatchery",
				"Melee Attacks Level 2", "Ground Carapace Level 2", "Hatchery",
				"Mutalisk" };
		int[] minutes = { 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 7, 7, 7, 7, 8,
				8, 9, 9, 9, 10, 10, 10, 10, 10, 12 };
		int[] seconds = { 15, 45, 50, 50, 55, 45, 45, 20, 20, 30, 40, 0, 0, 10,
				30, 40, 40, 20, 50, 20, 40, 40, 15, 30, 45, 45, 30, 15 };
		packBuildAndPutIntoDefaultBuilds(tempBuild, actions, minutes, seconds);

		ActionList tempBuild2 = new ActionList(
				"vZ -Pool first, Zergling/Baneling into Roach", Race.Zerg);
		String[] actions2 = { "Spawning Pool", "Hatchery", "Queen",
				"Extractor", "Queen", "Metabolic Boost", "Baneling Nest",
				"Spine Crawler", "Evolution Chamber", "Roach Warren",
				"Extractor", "Extractor", "Lair", "Missile Attacks Level 1",
				"Glial Reconstitution" };
		int[] minutes2 = { 2, 2, 3, 3, 4, 4, 5, 6, 7, 7, 7, 7, 7, 8, 9 };
		int[] seconds2 = { 0, 43, 5, 15, 10, 40, 5, 15, 15, 20, 20, 20, 30, 40,
				0 };
		packBuildAndPutIntoDefaultBuilds(tempBuild2, actions2, minutes2,
				seconds2);

		ActionList tempBuild3 = new ActionList(
				"vP -Pool first, 3 Hatch Roach Infestor", Race.Zerg);
		String[] actions3 = { "Spawning Pool", "Hatchery", "Queen", "Zergling",
				"Queen", "Hatchery", "Extractor", "Extractor",
				"Metabolic Boost", "Lair", "Evolution Chamber", "Extractor",
				"Hatchery", "Roach Warren", "Extractor", "Extractor",
				"Extractor", "Melee Attacks Level 1", "Evolution Chamber",
				"Infestation Pit", "Spire", "Hatchery", "Hatchery",
				"Ground Carapace Level 1", "Pathogen Glands",
				"Glial Reconstitution", "Hive", "Greater Spire" };
		int[] minutes3 = { 2, 2, 3, 3, 4, 4, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 8,
				8, 9, 9, 9, 9, 9, 9, 10, 10, 11, 12, };
		int[] seconds3 = { 0, 43, 5, 15, 0, 17, 0, 0, 10, 35, 40, 40, 0, 10,
				20, 20, 20, 40, 0, 15, 30, 30, 30, 50, 10, 30, 0, 50, };
		packBuildAndPutIntoDefaultBuilds(tempBuild3, actions3, minutes3,
				seconds3);

	}

	private static void protossBuilds() {
		ActionList tempBuild = new ActionList("vP - Phoenix 3 gate",
				Race.Protoss);
		String[] actions = { "Pylon", "Gateway", "Assimilator", "Pylon",
				"Cybernetics Core", "Assimilator", "Warp Gate", "Stargate",
				"Gateway", "Pylon", "Phoenix", "Gateway", "Pylon" };
		int[] minutes = { 0, 1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 5, 5, 5 };
		int[] seconds = { 50, 35, 55, 15, 45, 5, 35, 0, 15, 35, 55, 20, 30, 55 };
		packBuildAndPutIntoDefaultBuilds(tempBuild, actions, minutes, seconds);

		ActionList tempBuild2 = new ActionList("vZ - Sentry Immortal All-in",
				Race.Protoss);
		String[] actions2 = { "Pylon", "Nexus", "Forge", "Pylon", "Gateway",
				"Photon Cannon", "Assimilator", "Assimilator", "Pylon",
				"Cybernetics Core", "Warp Gate", "Robotics Facility",
				"Assimilator", "Ground Weapons Level 1", "Gateway", "Gateway",
				"Immortal", "Pylon", "Gateway", "Pylon", "Immortal", "Gateway",
				"Gateway", "Gateway", "Immortal", "Pylon", "Warp Prism" };

		int[] minutes2 = { 0, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6,
				7, 7, 7, 7, 8, 8, 8, 8, 8, 8 };
		int[] seconds2 = { 50, 25, 40, 0, 30, 30, 0, 0, 20, 45, 35, 40, 45, 20,
				30, 30, 55, 5, 15, 25, 30, 0, 0, 0, 15, 30, 45 };
		packBuildAndPutIntoDefaultBuilds(tempBuild2, actions2, minutes2,
				seconds2);

		ActionList tempBuild3 = new ActionList("vT - 1 Gate FE 3 , Colossi",
				Race.Protoss);
		String[] actions3 = { "Pylon", "Gateway", "Assimilator", "Pylon",
				"Cybernetics Core", "Warp Gate", "Nexus", "Stalker", "Pylon",
				"Assimilator", "Robotics Facility", "Gateway", "Gateway",
				"Pylon", "Assimilator", "Assimilator", "Pylon", "Forge",
				"Pylon", "Robotics Bay", "Ground Weapons Level 1", "Gateway",
				"Gateway", "Pylon", "Colossus", "Extended Thermal Lance",
				"Twlight Council", "Forge", "Colossus" };

		int[] minutes3 = { 0, 1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 6, 6, 6, 6, 6, 6,
				6, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 9 };
		int[] seconds3 = { 50, 30, 0, 15, 40, 30, 45, 5, 15, 30, 15, 0, 0, 10,
				15, 30, 45, 50, 30, 30, 45, 20, 20, 20, 40, 40, 50, 50, 40 };
		packBuildAndPutIntoDefaultBuilds(tempBuild3, actions3, minutes3,
				seconds3);

	}

	public static List<ActionList> populateDefaultBuilds(
			List<Action> allActionArgs) {
		allActions = allActionArgs;
		terranBuilds();
		protossBuilds();
		zergBuilds();
		return defaultBuilds;
	}
}
