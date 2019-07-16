package net.wavemark.cims.utility;

import java.util.List;

/**
 * The class <code>ReportHelper</code> exposes sets of data members, enums &
 * methods that are used across the reporting framework.
 */
public class ReportHelper {
	
	public static DataRow getDataRowFromIdentifier(List<DataRow> dataList, int rowIdentifier) {
		for (DataRow dataRow : dataList) {
			if (dataRow.getRowId() == rowIdentifier)
				return dataRow;
		}
		return null;
	}
}
