package net.wavemark.cims.utility;

import java.io.Serializable;
import java.util.List;

/**
 * The class <code>DataRow</code> represents a single row within a reporting
 * table. Each DataRow object contains a list of {@link DataCell} .
 */
public class DataRow implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ROW_ID = "ROWID";
	
	public static final String NAME = "Name";
	
	private int rowId;
	
	private List<DataCell> rowCells;

	

	public DataRow(List<DataCell> rowCells) {
		setRowId(Integer.MIN_VALUE);
		setRowCells(rowCells);
	}

	public DataRow(int rowId, List<DataCell> rowCells) {
		setRowId(rowId);
		setRowCells(rowCells);
	}

	public DataRow() {

	}

	/**
	 * Overrides the equals method
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof DataRow))
			return false;
		if (o == null)
			throw new IllegalArgumentException("Illegal Argument. Expecting DataRow. Found : NULL");
		DataRow dataRow = (DataRow) o;
		/* Compare the ids */
		if (this.rowId == dataRow.getRowId())
			return true;

		return false;
	}

	/**
	 * Looks up a matching DataRow object from a List<DataRow> by id.
	 * 
	 * @param dataRowList
	 *            the list of {@link DataRow}
	 * @param rowId
	 *            row identifier
	 * @return the matched {@lunk DataRow} or null
	 */
	public static DataRow getDataRowById(List<DataRow> dataRowList, int rowId) {
		/*
		 * if (dataRowList == null) throw new IllegalArgumentException(
		 * "Illegal Argument sent. Expecting List<DataRow>. Found Null");
		 */
		if (WebUtility.isEmpty(dataRowList)) {
			throw new IllegalArgumentException("Illegal Argument sent. Expecting List<DataRow>. Found Null");
		}

		for (DataRow item : dataRowList) {
			if (item != null && item.getRowId() == rowId)
				return item;
		}

		return null;
	}

	/**
	 * This method returns the DataCell in DataRow that has a certain name
	 * 
	 * @param cellName
	 * @return
	 */
	// public DataCell getCellByName(String cellName) {
	// DataCell returnedDataCell = null;
	//
	// DataCell dataCell = null;
	// for (int i = 0; i < rowCells.size(); i++) {
	// dataCell = rowCells.get(i);
	// if (dataCell.getCellName().equalsIgnoreCase(cellName)) {
	// returnedDataCell = dataCell;
	// break;
	// }
	// }
	//
	// return returnedDataCell;
	// }

	// FIXME no need to declare returnedDataCell as null, no need to declare
	// dataCell as null, no need to break --> replace by return, need to add
	// dataCell if not null and change the other condition from
	// dataCell.getCellName().equalsIgnoreCase(cellName) -->
	// cellName.equalsIgnoreCase(dataCell.getCellName()) because if
	// dataCell.getCellName() == null will be null pointer exception and must
	// add cellName not null and not empty in the beginning, and it should not
	// be equal ignore case

	public DataCell getCellByName(String cellName) {
		if (!WebUtility.isEmpty(cellName) && !WebUtility.isEmpty(rowCells)) {
			for (int i = 0; i < rowCells.size(); i++) {
				DataCell dataCell = rowCells.get(i);
				if (dataCell != null && cellName.equalsIgnoreCase(dataCell.getCellName())) {
					return dataCell;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String dataRow = "";
		for (DataCell cell : rowCells)
			dataRow += "\t" + cell;

		dataRow += "\n";

		return dataRow;
	}

	/* Setters & Getters */
	public void setRowCells(List<DataCell> rowCells) {
		this.rowCells = rowCells;
	}

	public List<DataCell> getRowCells() {
		return rowCells;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public int getRowId() {
		return rowId;
	}
}
