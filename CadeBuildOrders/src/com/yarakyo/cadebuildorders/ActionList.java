package com.yarakyo.cadebuildorders;

import java.io.Serializable;
import java.security.MessageDigest;
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
	Race Race;
	String hashId;

	public ActionList(List<Action> actionList, String buildName, Race vsRace) {
		this.actionList = actionList;
		this.buildName = buildName;
		this.Race = vsRace;
		claculateMD5();
	}

	public ActionList() {
		this.buildName = "";
		claculateMD5();
	}

	public ActionList(String buildName, Race vsRace) {
		this.buildName = buildName;
		this.Race = vsRace;
		claculateMD5();
	}

	// Getters
	public String getBuildName() {
		return this.buildName;
	}

	public List<Action> getActionList() {
		return this.actionList;
	}

	public Race getRace() {
		return this.Race;
	}
	
	public String getHash()
	{
		return this.hashId;
	}

	// Setters
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}
	
	//Methods
	private void claculateMD5()
	{
        String password = Race+buildName;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            this.hashId = sb.toString();
        } catch (Exception e) {
        }
	}
	
	

}
