package net.wavemark.cims.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class HospitalProduct {
	
	public static final String ACTIVE = "ACTIVE";
	
	public static final String WM_COMMENT = "ADDENTERPRISEPRODUCTTOOL";
	
	public static final String MANUFACTURER ="Manufacturer";

	private java.lang.String comments;

	private java.lang.Long consignedParLevel;

	private java.lang.Long currentCost;

	private java.lang.String distributorId;

	private java.lang.String emsChargeNumber;

	private java.lang.String hospitalId;

	private java.lang.String implantRegistrationFlg;

	private java.lang.String ignoreSuggestionsFlg;

	private java.lang.String lastUpdatedBy;

	private java.sql.Timestamp lastUpdatedDate;

	private java.lang.Double maxInventoryLevel;

	private java.lang.Double minInventoryLevel;

	private java.lang.String mmsItemNumber;

	private java.lang.String onHoldFlg;

	private java.lang.String orderIngProductId;

	private java.lang.String productId;

	private java.lang.String requestIngLocation;

	private java.lang.String rfIdFlg;

	private java.lang.Long standardOrderQty;

	private java.lang.String status;

	private java.lang.String supplierType;

	private java.lang.Double emergencyParlevel;

	private java.lang.Double optionalParlevel;

	private java.lang.Double temporaryParlevel;

	private java.lang.Double restockParlevel;

	private java.lang.String hospitalProductModel;

	private java.lang.Long hospitalOrderedQty;

	private java.lang.String hospitalProductName;

	private java.lang.String hospitalProductCategory;

	private java.lang.String mmsLocationId;

	private java.lang.String mmsVendorId;

	private java.lang.String orderReplacementFlg;

	private java.lang.String implantFlg;

	private java.lang.Double leadTime;

	private java.lang.Double orderIngIntervalDays;

	private java.lang.String wmComment;

	private java.lang.String consignedFlg;

	private java.lang.String hdsItemNumber;

	private java.lang.String mmsOrderIngProductuom;

	private java.lang.String biologicalImplantFlg;

	private java.lang.Long attachmentListId;

	private java.lang.String accountIngUnit;

	private java.lang.String glAccountMajor;

	private java.lang.String glAccountMinor;

	private java.lang.String orderInghOspitalId;

	private java.lang.String mmsSupplyLocationId;

	private java.lang.String expirationFlg;

	public HospitalProduct() {
	}

	/*
	 * public HospitalProduct(String comments, Long consignedParLevel, Long
	 * currentCost, String distributorId, String emsChargeNumber, String
	 * hospitalId, String implantRegistrationFlg, String ignoreSuggestionsFlg,
	 * String lastUpdatedBy, Timestamp lastUpdatedDate, Double
	 * maxInventoryLevel, Double minInventoryLevel, String mmsItemNumber, String
	 * onHoldFlg, String orderIngProductId, String productId, String
	 * requestIngLocation, String rfIdFlg, Long standardOrderQty, String status,
	 * String supplierType, Double emergencyParlevel, Double optionalParlevel,
	 * Double temporaryParlevel, Double restockParlevel, String
	 * hospitalProductModel, Long hospitalOrderedQty, String
	 * hospitalProductName, String hospitalProductCategory, String
	 * mmsLocationId, String mmsVendorId, String orderReplacementFlg, String
	 * implantFlg, Double leadTime, Double orderIngIntervalDays, String
	 * wmComment, String consignedFlg, String hdsItemNumber, String
	 * mmsOrderIngProductuom, String biologicalImplantFlg, Long
	 * attachmentListId, String accountIngUnit, String glAccountMajor, String
	 * glAccountMinor, String orderInghOspitalId, String mmsSupplyLocationId,
	 * String expirationFlg) {
	 * 
	 * this.comments = comments; this.consignedParLevel = consignedParLevel;
	 * this.currentCost = currentCost; this.distributorId = distributorId;
	 * this.emsChargeNumber = emsChargeNumber; this.hospitalId = hospitalId;
	 * this.implantRegistrationFlg = implantRegistrationFlg; this.lastUpdatedBy
	 * = lastUpdatedBy; this.lastUpdatedDate = lastUpdatedDate;
	 * this.maxInventoryLevel = maxInventoryLevel; this.minInventoryLevel =
	 * minInventoryLevel; this.mmsItemNumber = mmsItemNumber; this.onHoldFlg =
	 * onHoldFlg; this.orderIngProductId = orderIngProductId; this.productId =
	 * productId; this.requestIngLocation = requestIngLocation; this.rfIdFlg =
	 * rfIdFlg; this.standardOrderQty = standardOrderQty; this.status = status;
	 * this.supplierType = supplierType; this.wmComment = wmComment;
	 * this.emergencyParlevel = emergencyParlevel; this.optionalParlevel =
	 * optionalParlevel; this.temporaryParlevel = temporaryParlevel;
	 * this.restockParlevel = restockParlevel; this.leadTime = leadTime;
	 * this.orderIngIntervalDays = orderIngIntervalDays;
	 * this.ignoreSuggestionsFlg = ignoreSuggestionsFlg; }
	 */

//	public HospitalProduct(String comments, Long consignedParLevel, Long currentCost, String distributorId,
//			String emsChargeNumber, String hospitalId, String implantRegistrationFlg, String lastUpdatedBy,
//			Timestamp lastUpdatedDate, Double maxInventoryLevel, Double minInventoryLevel, String mmsItemNumber,
//			String onHoldFlg, String orderIngProductId, String productId, String requestIngLocation, String rfIdFlg,
//			Long standardOrderQty, String status, String supplierType, String wmComment, Double emergencyParlevel,
//			Double optionalParlevel, Double temporaryParlevel, Double restockParlevel, Double leadTime,
//			Double orderIngIntervalDays, String ignoreSuggestionsFlg){
//
//		this.comments = comments;
//		this.consignedParLevel = consignedParLevel;
//		this.currentCost = currentCost;
//		this.distributorId = distributorId;
//		this.emsChargeNumber = emsChargeNumber;
//		this.hospitalId = hospitalId;
//		this.implantRegistrationFlg = implantRegistrationFlg;
//		this.lastUpdatedBy = lastUpdatedBy;
//		this.lastUpdatedDate = lastUpdatedDate;
//		this.maxInventoryLevel = maxInventoryLevel;
//		this.minInventoryLevel = minInventoryLevel;
//		this.mmsItemNumber = mmsItemNumber;
//		this.onHoldFlg = onHoldFlg;
//		this.orderIngProductId = orderIngProductId;
//		this.productId = productId;
//		this.requestIngLocation = requestIngLocation;
//		this.rfIdFlg = rfIdFlg;
//		this.standardOrderQty = standardOrderQty;
//		this.status = status;
//		this.supplierType = supplierType;
//		this.wmComment = wmComment;
//		this.emergencyParlevel = emergencyParlevel;
//		this.optionalParlevel = optionalParlevel;
//		this.temporaryParlevel = temporaryParlevel;
//		this.restockParlevel = restockParlevel;
//		this.leadTime = leadTime;
//		this.orderIngIntervalDays = orderIngIntervalDays;
//		this.ignoreSuggestionsFlg = ignoreSuggestionsFlg;
//	}

	public java.lang.String getComments() {
		return comments;
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	public java.lang.Long getConsignedParLevel() {
		return consignedParLevel;
	}

	public void setConsignedParLevel(java.lang.Long consignedParLevel) {
		this.consignedParLevel = consignedParLevel;
	}

	public java.lang.Long getCurrentCost() {
		return currentCost;
	}

	public void setCurrentCost(java.lang.Long currentCost) {
		this.currentCost = currentCost;
	}

	public java.lang.String getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(java.lang.String distributorId) {
		this.distributorId = distributorId;
	}

	public java.lang.String getEmsChargeNumber() {
		return emsChargeNumber;
	}

	public void setEmsChargeNumber(java.lang.String emsChargeNumber) {
		this.emsChargeNumber = emsChargeNumber;
	}

	public java.lang.String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(java.lang.String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public java.lang.String getImplantRegistrationFlg() {
		return implantRegistrationFlg;
	}

	public void setImplantRegistrationFlg(java.lang.String implantRegistrationFlg) {
		this.implantRegistrationFlg = implantRegistrationFlg;
	}

	public java.lang.String getIgnoreSuggestionsFlg() {
		return ignoreSuggestionsFlg;
	}

	public void setIgnoreSuggestionsFlg(java.lang.String ignoreSuggestionsFlg) {
		this.ignoreSuggestionsFlg = ignoreSuggestionsFlg;
	}

	public java.lang.String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(java.lang.String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public java.sql.Timestamp getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(java.sql.Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public java.lang.Double getMaxInventoryLevel() {
		return maxInventoryLevel;
	}

	public void setMaxInventoryLevel(java.lang.Double maxInventoryLevel) {
		this.maxInventoryLevel = maxInventoryLevel;
	}

	public java.lang.Double getMinInventoryLevel() {
		return minInventoryLevel;
	}

	public void setMinInventoryLevel(java.lang.Double minInventoryLevel) {
		this.minInventoryLevel = minInventoryLevel;
	}

	public java.lang.String getMmsItemNumber() {
		return mmsItemNumber;
	}

	public void setMmsItemNumber(java.lang.String mmsItemNumber) {
		this.mmsItemNumber = mmsItemNumber;
	}

	public java.lang.String getOnHoldFlg() {
		return onHoldFlg;
	}

	public void setOnHoldFlg(java.lang.String onHoldFlg) {
		this.onHoldFlg = onHoldFlg;
	}

	public java.lang.String getOrderIngProductId() {
		return orderIngProductId;
	}

	public void setOrderIngProductId(java.lang.String orderIngProductId) {
		this.orderIngProductId = orderIngProductId;
	}

	public java.lang.String getProductId() {
		return productId;
	}

	public void setProductId(java.lang.String productId) {
		this.productId = productId;
	}

	public java.lang.String getRequestIngLocation() {
		return requestIngLocation;
	}

	public void setRequestIngLocation(java.lang.String requestIngLocation) {
		this.requestIngLocation = requestIngLocation;
	}

	public java.lang.String getRfIdFlg() {
		return rfIdFlg;
	}

	public void setRfIdFlg(java.lang.String rfIdFlg) {
		this.rfIdFlg = rfIdFlg;
	}

	public java.lang.Long getStandardOrderQty() {
		return standardOrderQty;
	}

	public void setStandardOrderQty(java.lang.Long standardOrderQty) {
		this.standardOrderQty = standardOrderQty;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(java.lang.String supplierType) {
		this.supplierType = supplierType;
	}

	public java.lang.Double getEmergencyParlevel() {
		return emergencyParlevel;
	}

	public void setEmergencyParlevel(java.lang.Double emergencyParlevel) {
		this.emergencyParlevel = emergencyParlevel;
	}

	public java.lang.Double getOptionalParlevel() {
		return optionalParlevel;
	}

	public void setOptionalParlevel(java.lang.Double optionalParlevel) {
		this.optionalParlevel = optionalParlevel;
	}

	public java.lang.Double getTemporaryParlevel() {
		return temporaryParlevel;
	}

	public void setTemporaryParlevel(java.lang.Double temporaryParlevel) {
		this.temporaryParlevel = temporaryParlevel;
	}

	public java.lang.Double getRestockParlevel() {
		return restockParlevel;
	}

	public void setRestockParlevel(java.lang.Double restockParlevel) {
		this.restockParlevel = restockParlevel;
	}

	public java.lang.String getHospitalProductModel() {
		return hospitalProductModel;
	}

	public void setHospitalProductModel(java.lang.String hospitalProductModel) {
		this.hospitalProductModel = hospitalProductModel;
	}

	public java.lang.Long getHospitalOrderedQty() {
		return hospitalOrderedQty;
	}

	public void setHospitalOrderedQty(java.lang.Long hospitalOrderedQty) {
		this.hospitalOrderedQty = hospitalOrderedQty;
	}

	public java.lang.String getHospitalProductName() {
		return hospitalProductName;
	}

	public void setHospitalProductName(java.lang.String hospitalProductName) {
		this.hospitalProductName = hospitalProductName;
	}

	public java.lang.String getHospitalProductCategory() {
		return hospitalProductCategory;
	}

	public void setHospitalProductCategory(java.lang.String hospitalProductCategory) {
		this.hospitalProductCategory = hospitalProductCategory;
	}

	public java.lang.String getMmsLocationId() {
		return mmsLocationId;
	}

	public void setMmsLocationId(java.lang.String mmsLocationId) {
		this.mmsLocationId = mmsLocationId;
	}

	public java.lang.String getMmsVendorId() {
		return mmsVendorId;
	}

	public void setMmsVendorId(java.lang.String mmsVendorId) {
		this.mmsVendorId = mmsVendorId;
	}

	public java.lang.String getOrderReplacementFlg() {
		return orderReplacementFlg;
	}

	public void setOrderReplacementFlg(java.lang.String orderReplacementFlg) {
		this.orderReplacementFlg = orderReplacementFlg;
	}

	public java.lang.String getImplantFlg() {
		return implantFlg;
	}

	public void setImplantFlg(java.lang.String implantFlg) {
		this.implantFlg = implantFlg;
	}

	public java.lang.Double getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(java.lang.Double leadTime) {
		this.leadTime = leadTime;
	}

	public java.lang.Double getOrderIngIntervalDays() {
		return orderIngIntervalDays;
	}

	public void setOrderIngIntervalDays(java.lang.Double orderIngIntervalDays) {
		this.orderIngIntervalDays = orderIngIntervalDays;
	}

	public java.lang.String getWmComment() {
		return wmComment;
	}

	public void setWmComment(java.lang.String wmComment) {
		this.wmComment = wmComment;
	}

	public java.lang.String getConsignedFlg() {
		return consignedFlg;
	}

	public void setConsignedFlg(java.lang.String consignedFlg) {
		this.consignedFlg = consignedFlg;
	}

	public java.lang.String getHdsItemNumber() {
		return hdsItemNumber;
	}

	public void setHdsItemNumber(java.lang.String hdsItemNumber) {
		this.hdsItemNumber = hdsItemNumber;
	}

	public java.lang.String getMmsOrderIngProductuom() {
		return mmsOrderIngProductuom;
	}

	public void setMmsOrderIngProductuom(java.lang.String mmsOrderIngProductuom) {
		this.mmsOrderIngProductuom = mmsOrderIngProductuom;
	}

	public java.lang.String getBiologicalImplantFlg() {
		return biologicalImplantFlg;
	}

	public void setBiologicalImplantFlg(java.lang.String biologicalImplantFlg) {
		this.biologicalImplantFlg = biologicalImplantFlg;
	}

	public java.lang.Long getAttachmentListId() {
		return attachmentListId;
	}

	public void setAttachmentListId(java.lang.Long attachmentListId) {
		this.attachmentListId = attachmentListId;
	}

	public java.lang.String getAccountIngUnit() {
		return accountIngUnit;
	}

	public void setAccountIngUnit(java.lang.String accountIngUnit) {
		this.accountIngUnit = accountIngUnit;
	}

	public java.lang.String getGlAccountMajor() {
		return glAccountMajor;
	}

	public void setGlAccountMajor(java.lang.String glAccountMajor) {
		this.glAccountMajor = glAccountMajor;
	}

	public java.lang.String getGlAccountMinor() {
		return glAccountMinor;
	}

	public void setGlAccountMinor(java.lang.String glAccountMinor) {
		this.glAccountMinor = glAccountMinor;
	}

	public java.lang.String getOrderInghOspitalId() {
		return orderInghOspitalId;
	}

	public void setOrderInghOspitalId(java.lang.String orderInghOspitalId) {
		this.orderInghOspitalId = orderInghOspitalId;
	}

	public java.lang.String getMmsSupplyLocationId() {
		return mmsSupplyLocationId;
	}

	public void setMmsSupplyLocationId(java.lang.String mmsSupplyLocationId) {
		this.mmsSupplyLocationId = mmsSupplyLocationId;
	}

	public java.lang.String getExpirationFlg() {
		return expirationFlg;
	}

	public void setExpirationFlg(java.lang.String expirationFlg) {
		this.expirationFlg = expirationFlg;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
