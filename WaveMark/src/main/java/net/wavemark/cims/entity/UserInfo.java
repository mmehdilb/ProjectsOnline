package net.wavemark.cims.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserInfo {

	private java.lang.String hospitalId;

	private java.lang.String userId;

	public UserInfo() {
	}

	public java.lang.String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(java.lang.String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
