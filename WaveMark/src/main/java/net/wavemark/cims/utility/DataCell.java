package net.wavemark.cims.utility;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.sql.Timestamp;

/**
 * The class <code>DataCell</code> represents a value within a reporting table.
 * Each cell value is part of a larger set that constitute a {@link DataRow}
 */
public class DataCell implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cellName;
	private Object cellValue;

	public DataCell(String cellName, Object value) {
		setCellName(cellName);
		setCellValue(value);
	}

	public void setCellValue(Object cellValue) {
		this.cellValue = cellValue;
	}

	public Object getCellValue() {
		return cellValue;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public String getCellName() {
		return cellName;
	}

	public String getStringValue() {
		try {
			if (!WebUtility.isEmpty(getStringValue())) {
				return getCellValue().toString();
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public Double getDouble() {
		try {
			if (!WebUtility.isEmpty(getStringValue())) {
				return (Double) getCellValue();
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public Long getLong() {
		Long value = null;
		try {
			value = (Long) getCellValue();
		} catch (Exception e) {
			value = null;
		}

		return value;
	}

	public Integer getInteger() {
		Integer value = null;
		try {
			value = (Integer) getCellValue();
		} catch (Exception e) {
			value = null;
		}

		return value;
	}

	public BigDecimal getBigDecimal() {
		BigDecimal value = null;
		try {
			value = (BigDecimal) getCellValue();
		} catch (Exception e) {
			value = null;
		}

		return value;
	}

	public Date getDate() {
		Date value = null;
		try {
			value = (Date) getCellValue();
		} catch (Exception e) {
			value = null;
		}

		return value;
	}

	public Timestamp getTimestamp() {
		Timestamp value = null;
		try {
			if (getCellValue() instanceof Timestamp) {
				value = (Timestamp) getCellValue();
				return value;
			} else if (getCellValue() instanceof Timestamp)
				return (Timestamp) getCellValue();
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public double getDoubleValue() {
		double value = 0;
		if (cellValue == null)
			value = 0;
		else if (cellValue instanceof Double)
			value = getDouble().doubleValue();
		else if (cellValue instanceof BigDecimal)
			value = getBigDecimal().doubleValue();

		return value;
	}

	public long getLongValue() {
		long value = 0;
		if (cellValue == null)
			value = 0;
		else if (cellValue instanceof Long)
			value = getLong().longValue();
		else if (cellValue instanceof BigDecimal)
			value = getBigDecimal().longValue();

		return value;
	}

	public boolean equals(Object object) {
		if (!(object instanceof DataCell))
			return false;
		DataCell cell = (DataCell) object;
		boolean equal = false;

		if (cell == null)
			return equal;

		if (cell.getCellName() != null && getCellName() == null)
			equal = false;
		else if (cell.getCellName() == null && getCellName() != null)
			equal = false;
		else if (cell.getCellName().equalsIgnoreCase(getCellName()))
			equal = true;

		return equal;
	}

	// @Override
	// public String toString() {
	// return cellValue == null ? "NULL" : cellValue.toString();
	// }

	// FIXME replaced by toStringBuilder
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}