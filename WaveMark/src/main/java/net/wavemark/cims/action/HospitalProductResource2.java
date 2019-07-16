package net.wavemark.cims.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import net.wavemark.cims.datamanagers.HSetupAddProductsDM;
import net.wavemark.cims.entity.HospitalProduct;
import net.wavemark.cims.entity.UserInfo;
import net.wavemark.cims.utility.AccessControlVariables;
import net.wavemark.cims.utility.DataRow;
import net.wavemark.cims.utility.ProductVariables;
import net.wavemark.cims.utility.ReportHelper;
import net.wavemark.cims.utility.Settings;
import net.wavemark.cims.utility.WebUtility;

public class HospitalProductResource2 extends BaseAction {

	List<HospitalProduct> endPointProducts;

	/*
	 * Declaring the variable that will map the order product info sent in JSON
	 * format
	 */
	// FIXME not used
	// private String addProductsList;
	// Check if a product that belongs to another mfr was added
	private boolean newProductOfMfrAdded = false;

	// FIXME not used
	// String endPointProductsFailed;

	// FIXME if exception no need to parse exception
	public void addHospitalProducts(String addedProductsList) throws Exception {
		UserInfo hospUser = (UserInfo) getSession().get(Settings.SESSION_USERINFO);

		// FIXME we can set it directly as
		// Calendar.getInstance().getTimeInMillis() without creating 2 variables
		// currentDateCalendar and currentTime
		// Calendar currentDateCalendar = Calendar.getInstance();
		// Timestamp currentTime = new
		// Timestamp(currentDateCalendar.getTimeInMillis());

		List<HospitalProduct> endpointProductList = new ArrayList<HospitalProduct>();

		// addedProductsList is the list of products added by the user
		JSONArray jsonAddedProdList = new JSONArray(addedProductsList);

		setNewProductOfMfrAdded(false);

		// productsDataset is the list of cached data on the server.
		List<DataRow> productsDataset = new HSetupAddProductsDM().getEnterpriseProducts();
		for (int i = 0; i < jsonAddedProdList.length(); i++) {
			JSONObject product = jsonAddedProdList.getJSONObject(i);
			// get Data from JSON : ROWID and NonRfidFlag
			int rowId = product.getInt(ProductVariables.ROW_ID);

			// get Data from cached data: using the rowid
			DataRow productRow = ReportHelper.getDataRowFromIdentifier(productsDataset, rowId);

			HospitalProduct singleEndpointProduct = new HospitalProduct();

			populateCommonFields(hospUser, productRow, singleEndpointProduct);

			populateRfiByBarCodeFlag(product, singleEndpointProduct);

			// FIXME is not used
			// String mfrID =
			// productRow.getCellByName(HSetupAddProductsDM.COL_MFR_ID).getCellValue().toString();

			// String glaccountmajor = productValueByCellName(productRow,
			// HSetupAddProductsDM.COL_GLACCOUNT_MAJOR);
			// String glaccountminor = productValueByCellName(productRow,
			// HSetupAddProductsDM.COL_GLACCOUNT_MINOR);
			// String biologicalImplantFlg = productValueByCellName(productRow,
			// HSetupAddProductsDM.COL_BIOLOGICAL_IMPLANT_FLG);

			// FIXME removed in has("TRACKBY") setted as
			// product.getString("TRACKBY") else as
			// productValueByCellName(productRow,
			// HSetupAddProductsDM.COL_PRODUCT_ID)
			// String trackByProductId = productValueByCellName(productRow,
			// HSetupAddProductsDM.COL_PRODUCT_ID);
			// FIXME removed and setted only in has("TRACKBY")
			// String locationID = null;
			// FIXME removed and setted only in has("TRACKBY")
			// String itemNumber = null;
			// FIXME removed and setted only in has("TRACKBY")
			// String distributorID = null;
			// String vendorID = null;
			// FIXME setted inside both
			// String productCatagory = null;
			// Double currentCost = null;
			// String encryptedCost = null;
			// String supplierType = ;
			// String implantFlg = ProductVariables.NO;
			// String barcodeFlag = null;
			// String rfidFlg = ProductVariables.YES;
			// String consignedflg = ProductVariables.NO;
			// boolean hasAccessToProductCategory =
			// envelopeValue(AccessControlVariables.HOSPITAL_CATEGORY);
			// boolean hasAccessToEditCost =
			// envelopeValue(HSetupAddProductsDM.COL_COST);
			// boolean hasAccessToPricingModule =
			// envelopeValue(AccessControlVariables.PRICING_MODULE);
			// boolean hasAccessToEditGlAccount =
			// envelopeValue(AccessControlVariables.CAN_EDIT_GL_ACCOUNT);

			if (product.has(ProductVariables.TRACK_BY)) {
				mappingProductHavingTrackBy(product, productRow, singleEndpointProduct);

				/*
				 * if (product.has(ProductVariables.BARCODE_FLAG)) { barcodeFlag
				 * = product.getString(ProductVariables.BARCODE_FLAG); if
				 * (barcodeFlag.equalsIgnoreCase(ProductVariables.YES)) {
				 * rfidFlg = ProductVariables.NO; } }
				 */
			} else {

				mappingProductNotHavingTrackBy(product, productRow, singleEndpointProduct);

				/*
				 * if (product.has(ProductVariables.BARCODE_FLAG)) { barcodeFlag
				 * = product.getString(ProductVariables.BARCODE_FLAG); if
				 * (barcodeFlag.equalsIgnoreCase(ProductVariables.YES)) rfidFlg
				 * = ProductVariables.NO; }
				 */
			}

			endpointProductList.add(singleEndpointProduct);

			/* Check if the product added belong to a new mfr */
			manipulateNewProductAdded(productRow);
		}

		this.endPointProducts = endpointProductList;
	}

	private void manipulateNewProductAdded(DataRow productRow) {
		if (havingNewProductAdded()) {
			String mfrName = productValueByCellName(productRow, HSetupAddProductsDM.COL_MFR_NAME);

			List<DataRow> mfrDropdownData = obtainMfrDropdawunDataList();

			// Check if the list contains that mfr
			// if (mfrDropdownData != null && mfrDropdownData.size() > 0) {
			if (!WebUtility.isEmpty(mfrDropdownData)) {
				setNewProductOfMfrAdded(true);
				for (DataRow mfrRow : mfrDropdownData) {
					// if
					// (mfrName.equalsIgnoreCase(mfrRow.getCellByName("Name").getCellValue().toString()))
					// {
					if (mfrName != null && mfrName.equals(productValueByCellName(mfrRow, DataRow.NAME))) {
						setNewProductOfMfrAdded(false);
						break;
					}
				}
			}
		}
	}

	private List<DataRow> obtainMfrDropdawunDataList() {
		List<DataRow> mfrDropdownData = (List<DataRow>) getSession().get("InventoryCatalogMfrDropdownValues");
		// if (mfrDropdownData == null || mfrDropdownData.size() == 0)
		if (WebUtility.isEmpty(mfrDropdownData)) {
			mfrDropdownData = (List<DataRow>) getSession().get("ImplantCatalogMfrDropdownValues");
		}
		return mfrDropdownData;
	}

	private boolean havingNewProductAdded() {
		return !isNewProductOfMfrAdded() && (getSession().containsKey("InventoryCatalogMfrDropdownValues")
				|| getSession().containsKey("ImplantCatalogMfrDropdownValues"));
	}

	private void mappingProductNotHavingTrackBy(JSONObject product, DataRow productRow,
			HospitalProduct singleEndpointProduct) {
		singleEndpointProduct.setProductId(productValueByCellName(productRow, HSetupAddProductsDM.COL_PRODUCT_ID));
		singleEndpointProduct.setSupplierType(HospitalProduct.MANUFACTURER);
		populateProductCategory(product, singleEndpointProduct);
		populateMmsVendorId(product, singleEndpointProduct);
		populateImplantFlag(product, singleEndpointProduct);
		populateBiologicalImpFlag(product, singleEndpointProduct);
		populateProductMajorAndMinor(product, singleEndpointProduct);
		populateCurrentCost(product, singleEndpointProduct);
	}

	private void mappingProductHavingTrackBy(JSONObject product, DataRow productRow,
			HospitalProduct singleEndpointProduct) {
		singleEndpointProduct.setProductId(product.getString(ProductVariables.TRACK_BY));
		singleEndpointProduct.setMmsLocationId(productValueByCellName(productRow, HSetupAddProductsDM.COL_LOCATION));
		singleEndpointProduct.setMmsItemNumber(productValueByCellName(productRow, HSetupAddProductsDM.COL_ITEM_NUMBER));
		singleEndpointProduct.setHospitalProductCategory(
				productValueByCellName(productRow, HSetupAddProductsDM.COL_PRODUCT_CATEGORY));
		singleEndpointProduct
				.setDistributorId(productValueByCellName(productRow, HSetupAddProductsDM.COL_DISTRIBUTORID));
		singleEndpointProduct
				.setSupplierType(productValueByCellName(productRow, HSetupAddProductsDM.COL_SUPPLIER_TYPE));
		singleEndpointProduct.setImplantFlg(productValueByCellName(productRow, HSetupAddProductsDM.COL_IS_IMPLANTABLE));
		singleEndpointProduct.setConsignedFlg(productValueByCellName(productRow, HSetupAddProductsDM.COL_IS_CONSIGNED));
		singleEndpointProduct.setMmsVendorId(productValueByCellName(productRow, HSetupAddProductsDM.COL_MMSVENDORID));

		calculateCurrentCost(product, productRow, singleEndpointProduct);
	}

	private void populateCurrentCost(JSONObject product, HospitalProduct singleEndpointProduct) {
		if (hasEnvelopeValue(HSetupAddProductsDM.COL_COST)) {
			if (havinProductCost(product)) {
				Double currentCost = product.getDouble(ProductVariables.COST);
				if (currentCost != null) {
					singleEndpointProduct.setCurrentCost(currentCost.longValue());
				}
			}
		}
	}

	private boolean havinProductCost(JSONObject product) {
		return product.has(ProductVariables.COST) && hasEnvelopeValue(AccessControlVariables.PRICING_MODULE)
				&& !WebUtility.isEmpty(product.getString(ProductVariables.COST));
	}

	private void populateProductMajorAndMinor(JSONObject product, HospitalProduct singleEndpointProduct) {
		if (hasEnvelopeValue(AccessControlVariables.CAN_EDIT_GL_ACCOUNT)) {
			if (havingProductMajor(product)) {
				singleEndpointProduct.setGlAccountMajor(product.getString(ProductVariables.GL_ACCOUNT_MAJOR));
			}
			// FIXME must be minor ??
			if (havingProductMinor(product)) {
				singleEndpointProduct.setGlAccountMinor(product.getString(ProductVariables.GL_ACCOUNT_MINOR));
			}
		}
	}

	private void populateBiologicalImpFlag(JSONObject product, HospitalProduct singleEndpointProduct) {
		if (product.has(ProductVariables.BIOLOGICAL_IMPLANT_FLG)) {
			singleEndpointProduct
					.setBiologicalImplantFlg(valueByProductBoolean(product, ProductVariables.BIOLOGICAL_IMPLANT_FLG));
		}
	}

	private void populateImplantFlag(JSONObject product, HospitalProduct singleEndpointProduct) {
		if (product.has(ProductVariables.IMPLANT_FLG)) {
			singleEndpointProduct.setImplantFlg(valueByProductBoolean(product, ProductVariables.IMPLANT_FLG));
		}
	}

	private void populateMmsVendorId(JSONObject product, HospitalProduct singleEndpointProduct) {
		if (product.has(ProductVariables.VENDOR_ID) && product.getString(ProductVariables.VENDOR_ID) != null) {
			singleEndpointProduct.setMmsVendorId(product.getString(ProductVariables.VENDOR_ID));
		}
	}

	private void populateProductCategory(JSONObject product, HospitalProduct singleEndpointProduct) {
		if (havingProductCategory(product)) {
			singleEndpointProduct.setHospitalProductCategory(product.getString(ProductVariables.PRODUCT_CATEGORY));
		}
	}

	private boolean havingProductMinor(JSONObject product) {
		return product.has(ProductVariables.GL_ACCOUNT_MINOR) && product.get(ProductVariables.GL_ACCOUNT_MINOR) != null
				&& product.getString(ProductVariables.GL_ACCOUNT_MINOR) != null;
	}

	private boolean havingProductMajor(JSONObject product) {
		return product.has(ProductVariables.GL_ACCOUNT_MAJOR) && product.get(ProductVariables.GL_ACCOUNT_MAJOR) != null
				&& product.getString(ProductVariables.GL_ACCOUNT_MAJOR) != null;
	}

	private boolean havingProductCategory(JSONObject product) {
		return product.has(ProductVariables.PRODUCT_CATEGORY)
				&& hasEnvelopeValue(AccessControlVariables.HOSPITAL_CATEGORY)
				&& product.getString(ProductVariables.PRODUCT_CATEGORY) != null;
	}

	private void calculateCurrentCost(JSONObject product, DataRow productRow, HospitalProduct singleEndpointProduct) {
		BigDecimal cost = productRow.getCellByName(HSetupAddProductsDM.COL_COST).getBigDecimal();
		if (cost != null) {
			Double currentCost = cost.doubleValue() * calculateEnclosedQuantity(product);
			if (currentCost != null) {
				singleEndpointProduct.setCurrentCost(currentCost.longValue());
			}
		}
	}

	private int calculateEnclosedQuantity(JSONObject product) {
		if (product.has(ProductVariables.ENCLOSED_QTY)) {
			return product.getInt(ProductVariables.ENCLOSED_QTY);
		}
		return 1;
	}

	private String valueByProductBoolean(JSONObject product, String type) {
		return product.getBoolean(type) ? ProductVariables.YES : ProductVariables.NO;
	}

	private boolean hasEnvelopeValue(String property) {
		return getAccessControlEnvelope().hasAccessToProperty(property);
	}

	private void populateRfiByBarCodeFlag(JSONObject product, HospitalProduct singleEndpointProduct) {
		singleEndpointProduct.setRfIdFlg(ProductVariables.YES);
		// once method for if and else
		if (product.has(ProductVariables.BARCODE_FLAG)) {
			// FIXME no need to create variable
			// barcodeFlag = product.getString(ProductVariables.BARCODE_FLAG);
			// FIXME if barcodeFlag is null will go in null pointer
			// exception
			// if (barcodeFlag.equalsIgnoreCase(ProductVariables.YES)) {
			if (ProductVariables.YES.equalsIgnoreCase(product.getString(ProductVariables.BARCODE_FLAG))) {
				singleEndpointProduct.setRfIdFlg(ProductVariables.NO);
			}
		}
	}

	private void populateCommonFields(UserInfo hospUser, DataRow productRow, HospitalProduct singleEndpointProduct) {

		singleEndpointProduct.setComments(productValueByCellName(productRow, HSetupAddProductsDM.COL_COMMENTS));
		singleEndpointProduct.setConsignedParLevel(new Long(0));
		singleEndpointProduct
				.setEmsChargeNumber(productValueByCellName(productRow, HSetupAddProductsDM.COL_EMSCHARGENUMBER));
		singleEndpointProduct.setImplantRegistrationFlg(ProductVariables.NO);
		singleEndpointProduct.setLastUpdatedBy(hospUser.getUserId());
		singleEndpointProduct.setHospitalId(hospUser.getHospitalId());
		singleEndpointProduct.setLastUpdatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		singleEndpointProduct.setMaxInventoryLevel(new Double(0.0001));
		singleEndpointProduct.setMinInventoryLevel(new Double(0.0001));
		singleEndpointProduct.setOnHoldFlg(ProductVariables.NO);
		singleEndpointProduct
				.setOrderIngProductId(productValueByCellName(productRow, HSetupAddProductsDM.COL_PRODUCT_ID));
		singleEndpointProduct.setRequestIngLocation(null);
		singleEndpointProduct.setStandardOrderQty(new Long(0));
		singleEndpointProduct.setStatus(HospitalProduct.ACTIVE);
		singleEndpointProduct.setWmComment(HospitalProduct.WM_COMMENT);
		singleEndpointProduct.setEmergencyParlevel(new Double(0.0001));
		singleEndpointProduct.setOptionalParlevel(new Double(0.0001));
		singleEndpointProduct.setTemporaryParlevel(new Double(0.0001));
		singleEndpointProduct.setRestockParlevel(new Double(0.0001));
		singleEndpointProduct.setLeadTime(new Double(0.0001));
		singleEndpointProduct.setOrderIngIntervalDays(new Double(0.0001));
		singleEndpointProduct.setIgnoreSuggestionsFlg(ProductVariables.NO);
		singleEndpointProduct
				.setHospitalProductName(productValueByCellName(productRow, HSetupAddProductsDM.COL_PRODUCT_NAME));
		singleEndpointProduct.setMmsOrderIngProductuom(
				productValueByCellName(productRow, HSetupAddProductsDM.COL_ORDERING_PRODUCT_UOM));
		singleEndpointProduct.setOrderReplacementFlg(ProductVariables.NO);
		singleEndpointProduct
				.setHdsItemNumber(productValueByCellName(productRow, HSetupAddProductsDM.COL_HDS_ITEMNUMBER));
		singleEndpointProduct
				.setOrderInghOspitalId(productValueByCellName(productRow, HSetupAddProductsDM.COL_ORDERING_ENDPOINTID));
		singleEndpointProduct.setMmsSupplyLocationId(
				productValueByCellName(productRow, HSetupAddProductsDM.COL_MMSSUPPLY_LOCATION_ID));

		// setted as default N
		singleEndpointProduct.setConsignedFlg(ProductVariables.NO);
		// setted as default if not has("TRACKBY") and
		// product.has(ProductVariables.BIOLOGICAL_IMPLANT_FLG) will change the
		// value
		singleEndpointProduct.setBiologicalImplantFlg(
				productValueByCellName(productRow, HSetupAddProductsDM.COL_BIOLOGICAL_IMPLANT_FLG));
		singleEndpointProduct
				.setGlAccountMajor(productValueByCellName(productRow, HSetupAddProductsDM.COL_GLACCOUNT_MAJOR));
		singleEndpointProduct
				.setGlAccountMinor(productValueByCellName(productRow, HSetupAddProductsDM.COL_GLACCOUNT_MINOR));
	}

	public void setNewProductOfMfrAdded(boolean newProductOfMfrAdded) {
		this.newProductOfMfrAdded = newProductOfMfrAdded;
	}

	public boolean isNewProductOfMfrAdded() {
		return newProductOfMfrAdded;
	}

	private String productValueByCellName(DataRow productRow, String columnName) {
		if (!WebUtility.isEmpty(productRow) && !WebUtility.isEmpty(columnName)) {
			if (!WebUtility.isEmpty(productRow.getCellByName(columnName))) {
				return productRow.getCellByName(columnName).getStringValue();
			}
		}
		return null;
	}
}