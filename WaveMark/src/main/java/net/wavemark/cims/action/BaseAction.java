package net.wavemark.cims.action;

import java.util.Map;

import net.wavemark.cims.utility.AccessControlEnvelope;

public class BaseAction {
	/* Declare the mode i.e. DEFAULT, LOAD_REPORT etc... */
	private String mode;

	/* Declare the session object */
	private Map session;

	/* Declaring report name */
	private String reportName;

	/*
	 * Declaring the object that will hold the access control of each action
	 * class
	 */
	private AccessControlEnvelope accessControlEnvelope;

	/* Setters & Getters */
	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public Map getSession() {
		return session;
	}

	public String getReportName() {
		return reportName;
	}

	public void setAccessControlEnvelope(AccessControlEnvelope accessMap) {
		this.accessControlEnvelope = accessMap;
	}

	public AccessControlEnvelope getAccessControlEnvelope() {
		return accessControlEnvelope;
	}

	public boolean hasAccessToProperty(String propertyId) {
		return this.accessControlEnvelope.hasAccessToProperty(propertyId);
	}
}