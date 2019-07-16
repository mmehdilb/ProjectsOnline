package net.wavemark.cims.utility;

import java.util.List;

public class WebUtility {

	public static final boolean isEmpty(String arg) {
		return arg == null || "".equals(arg);
	}

	public static boolean isEmpty(List list) {
		return list == null || list.isEmpty();
	}
	
	public static boolean isEmpty(Object currentObject) {
		if (currentObject == null || "".equals(currentObject)) {
			return true;
		}
		return false;
	}


}
