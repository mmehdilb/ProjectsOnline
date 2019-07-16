package net.wavemark.cims.utility;

import java.util.HashMap;

public class AccessControlEnvelope {

	HashMap accessMap;

	public AccessControlEnvelope() {
		this.accessMap = new HashMap();
	}

	// public boolean hasAccessToProperty(String propertyId) {
	// Object property = getAccessMap().get(propertyId);
	//
	// if (property == null)
	// return false;
	// else
	// return true;
	// }

	// FIXME me no need to else
	
	public boolean hasAccessToProperty(String propertyId) {
		Object property = getAccessMap().get(propertyId);

		if (property == null) {
			return false;
		}
		return true;
	}

	public void setAccessMap(HashMap accessMap) {
		this.accessMap = accessMap;
	}

	public HashMap getAccessMap() {
		return accessMap;
	}
}
