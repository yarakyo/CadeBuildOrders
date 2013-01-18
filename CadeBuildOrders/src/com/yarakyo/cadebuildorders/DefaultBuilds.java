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
				"Orbital Command", "Tech Lab", "Supply Depot", "Starport" };
		int[] minutes = { 1, 1, 1, 3, 3, 3, 3, 4 };
		int[] seconds = { 0, 20, 57, 4, 20, 35, 40, 7 };
		packBuildAndPutIntoDefaultBuilds(tempBuild, actions, minutes, seconds);

		ActionList tempBuild2 = new ActionList("vZ - 3 CC Dual port Banshee",
				Race.Terran);
		String[] actions2 = { "Supply Depot", "Command Center", "Barracks",
				"Refinery", "Orbital Command", "Orbital Command",
				"Command Center", "Refinery", "Factory", "Barracks",
				"Engineering Bay", "Bunker", "Hellion", "Starport", "Starport",
				"Orbital Command", "Supply Depot", "Tech Lab", "Tech Lab" };
		int[] minutes2 = { 0, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 6,
				7, 7 };
		int[] seconds2 = { 58, 26, 50, 3, 56, 9, 43, 54, 19, 20, 34, 50, 26,
				30, 30, 39, 55, 23, 23 };

		packBuildAndPutIntoDefaultBuilds(tempBuild2, actions2, minutes2,
				seconds2);

		ActionList tempBuild3 = new ActionList("vP - Command Center first",
				Race.Terran);
		String[] actions3 = { "Supply Depot", "Command Center", "Barracks",
				"Barracks", "Orbital Command", "Marine", "Orbital Command",
				"Bunker", "Barracks", "Barracks", "Refinery", "Refinery",
				"Supply Depot" };
		int[] minutes3 = { 0, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5, 5, 5 };
		int[] seconds3 = { 52, 28, 52, 24, 58, 59, 11, 18, 9, 12, 38, 39, 40 };

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
