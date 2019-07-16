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
import net.wavemark.cims.utility.DataRow;
import net.wavemark.cims.utility.ReportHelper;
import net.wavemark.cims.utility.Settings;
import net.wavemark.cims.utility.WebUtility;

public class HospitalProductResourceOld extends BaseAction {

	List<HospitalProduct> endPointProducts;

	/*
	 * Declaring the variable that will map the order product info sent in JSON
	 * format
	 */
	private String addProductsList;
	// Check if a product that belongs to another mfr was added
	private boolean newProductOfMfrAdded = false;
	String endPointProductsFailed;

	// FIXME if exception no need to parse exception
	public void addHospitalProducts(String addedProductsList) throws Exception {
		UserInfo hospUser = (UserInfo) getSession().get(Settings.SESSION_USERINFO);
		String endpointID = hospUser.getHospitalId();
		String userID = hospUser.getUserId();

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
			int rowId = product.getInt("ROWID");

			// get Data from cached data: using the rowid
			DataRow productRow = ReportHelper.getDataRowFromIdentifier(productsDataset, rowId);

			// String mfrID =
			// productRow.getCellByName(HSetupAddProductsDM.COL_MFR_ID).getCellValue().toString();
			String mfrName = productValueByCellName(productRow, HSetupAddProductsDM.COL_MFR_NAME);
			String trackByProductId = productValueByCellName(productRow, HSetupAddProductsDM.COL_PRODUCT_ID);
			String orderingProductID = productValueByCellName(productRow, HSetupAddProductsDM.COL_PRODUCT_ID);

			String divisionID = productValueByCellName(productRow, HSetupAddProductsDM.COL_DIVISION_ID);
			String productGroupID = productValueByCellName(productRow, HSetupAddProductsDM.COL_PRODGROUP_ID);

			String itemDescription = productValueByCellName(productRow, HSetupAddProductsDM.COL_PRODUCT_NAME);
			String orderingUOM = productValueByCellName(productRow, HSetupAddProductsDM.COL_ORDERING_PRODUCT_UOM);
			String emsChargeNumber = productValueByCellName(productRow, HSetupAddProductsDM.COL_EMSCHARGENUMBER);
			String glaccountmajor = productValueByCellName(productRow, HSetupAddProductsDM.COL_GLACCOUNT_MAJOR);
			String glaccountminor = productValueByCellName(productRow, HSetupAddProductsDM.COL_GLACCOUNT_MINOR);
			String hdsItemNumber = productValueByCellName(productRow, HSetupAddProductsDM.COL_HDS_ITEMNUMBER);
			String orderingEndpointId = productValueByCellName(productRow, HSetupAddProductsDM.COL_ORDERING_ENDPOINTID);
			String mmssupplyLocationID = productValueByCellName(productRow,
					HSetupAddProductsDM.COL_MMSSUPPLY_LOCATION_ID);
			String biologicalImplantFlg = productValueByCellName(productRow,
					HSetupAddProductsDM.COL_BIOLOGICAL_IMPLANT_FLG);

			String comments = productValueByCellName(productRow, HSetupAddProductsDM.COL_COMMENTS);
			String locationID = null;
			String itemNumber = null;
			String distributorID = null;
			String vendorID = null;
			String productCatagory = null;
			Double currentCost = null;
			// String encryptedCost = null;
			String supplierType = "Manufacturer";
			String implantFlg = "N";
			String barcodeFlag = null;
			String rfidFlg = "Y";
			String consignedflg = "N";
			boolean hasAccessToProductCategory = getAccessControlEnvelope().hasAccessToProperty("hospCategory");
			boolean hasAccessToEditCost = getAccessControlEnvelope().hasAccessToProperty(HSetupAddProductsDM.COL_COST);
			boolean hasAccessToPricingModule = getAccessControlEnvelope().hasAccessToProperty("pricingModule");
			boolean hasAccessToEditGlAccount = getAccessControlEnvelope().hasAccessToProperty("canEditGlAccount");

			if (product.has("TRACKBY")) {
				trackByProductId = product.getString("TRACKBY");

				int enclosedQty = 1;
				if (product.has("ENCLOSEDQTY"))
					enclosedQty = product.getInt("ENCLOSEDQTY");

				locationID = productValueByCellName(productRow, HSetupAddProductsDM.COL_LOCATION);
				itemNumber = productValueByCellName(productRow, HSetupAddProductsDM.COL_ITEM_NUMBER);
				productCatagory = productValueByCellName(productRow, HSetupAddProductsDM.COL_PRODUCT_CATEGORY);
				distributorID = productValueByCellName(productRow, HSetupAddProductsDM.COL_DISTRIBUTORID);
				vendorID = productValueByCellName(productRow, HSetupAddProductsDM.COL_MMSVENDORID);
				supplierType = productValueByCellName(productRow, HSetupAddProductsDM.COL_SUPPLIER_TYPE);
				implantFlg = productValueByCellName(productRow, HSetupAddProductsDM.COL_IS_IMPLANTABLE);
				consignedflg = productValueByCellName(productRow, HSetupAddProductsDM.COL_IS_CONSIGNED);
				BigDecimal cost = productRow.getCellByName(HSetupAddProductsDM.COL_COST).getBigDecimal();
				if (cost != null) {
					currentCost = cost.doubleValue();
					currentCost = currentCost * enclosedQty;
				}
				if (product.has("BARCODEFLAG")) {
					barcodeFlag = product.getString("BARCODEFLAG");
					if (barcodeFlag.equalsIgnoreCase("Y"))
						rfidFlg = "N";
				}
			} else {

				if (product.has("PRODUCTCATEGORY") && hasAccessToProductCategory
						&& product.getString("PRODUCTCATEGORY") != null)
					productCatagory = product.getString("PRODUCTCATEGORY");
				if (product.has("VENDORID") && product.getString("VENDORID") != null)
					vendorID = product.getString("VENDORID");
				if (product.has("IMPLANTFLG"))
					implantFlg = product.getBoolean("IMPLANTFLG") ? "Y" : "N";
				if (product.has("BIOLOGICALIMPLANTFLG")) {
					biologicalImplantFlg = product.getBoolean("BIOLOGICALIMPLANTFLG") ? "Y" : "N";
				}
				if (hasAccessToEditGlAccount) {
					if (product.has("GLACCOUNTMAJOR") && product.get("GLACCOUNTMAJOR") != null
							&& product.getString("GLACCOUNTMAJOR") != null)
						glaccountmajor = product.getString("GLACCOUNTMAJOR");

					if (product.has("GLACCOUNTMINOR") && product.get("GLACCOUNTMAJOR") != null
							&& product.getString("GLACCOUNTMINOR") != null)
						glaccountminor = product.getString("GLACCOUNTMINOR");
				}

				if (hasAccessToEditCost) {
					if (product.has("COST") && hasAccessToPricingModule && product.getString("COST") != null
							&& !product.getString("COST").trim().equals(""))
						currentCost = product.getDouble("COST");
				}

				if (product.has("BARCODEFLAG")) {
					barcodeFlag = product.getString("BARCODEFLAG");
					if (barcodeFlag.equalsIgnoreCase("Y"))
						rfidFlg = "N";
				}
			}
			HospitalProduct singleEndpointProduct = new HospitalProduct();
//			HospitalProduct singleEndpointProduct = new HospitalProduct(comments, new Long(0), currentCost.longValue(),
//					distributorID, emsChargeNumber, endpointID, "N", userID,
//					new Timestamp(Calendar.getInstance().getTimeInMillis()), new Double(0.0001), new Double(0.0001),
//					itemNumber, "N", orderingProductID, trackByProductId, null, rfidFlg, new Long(0), "ACTIVE",
//					supplierType, "ADDENTERPRISEPRODUCTTOOL", new Double(0.0001), new Double(0.0001),
//					new Double(0.0001), new Double(0.0001), new Double(0.0001), new Double(0.0001), "N");
			singleEndpointProduct.setMmsLocationId(locationID);
			singleEndpointProduct.setMmsVendorId(vendorID);
			singleEndpointProduct.setHospitalProductCategory(productCatagory);
			singleEndpointProduct.setHospitalProductName(itemDescription);
			singleEndpointProduct.setImplantFlg(implantFlg);
			singleEndpointProduct.setBiologicalImplantFlg(biologicalImplantFlg);
			singleEndpointProduct.setMmsOrderIngProductuom(orderingUOM);
			singleEndpointProduct.setConsignedFlg(consignedflg);
			singleEndpointProduct.setOrderReplacementFlg("N");
			singleEndpointProduct.setGlAccountMajor(glaccountmajor);
			singleEndpointProduct.setGlAccountMinor(glaccountminor);
			singleEndpointProduct.setHdsItemNumber(hdsItemNumber);
			singleEndpointProduct.setOrderInghOspitalId(orderingEndpointId);
			singleEndpointProduct.setMmsSupplyLocationId(mmssupplyLocationID);

			endpointProductList.add(singleEndpointProduct);

			/* Check if the product added belong to a new mfr */
			if (!isNewProductOfMfrAdded() && (getSession().containsKey("InventoryCatalogMfrDropdownValues")
					|| getSession().containsKey("ImplantCatalogMfrDropdownValues"))) {
				List<DataRow> mfrDropdownData = (List<DataRow>) getSession().get("InventoryCatalogMfrDropdownValues");
				if (mfrDropdownData == null || mfrDropdownData.size() == 0)
					mfrDropdownData = (List<DataRow>) getSession().get("ImplantCatalogMfrDropdownValues");

				// Check if the list contains that mfr
				if (mfrDropdownData != null && mfrDropdownData.size() > 0) {
					setNewProductOfMfrAdded(true);
					for (DataRow mfrRow : mfrDropdownData) {
						if (mfrName.equalsIgnoreCase(mfrRow.getCellByName("Name").getCellValue().toString())) {
							setNewProductOfMfrAdded(false);
							break;
						}
					}
				}
			}
		}

		this.endPointProducts = endpointProductList;
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

	private String productBigDecimalValueByCellName(DataRow productRow, String columnName) {
		if (!WebUtility.isEmpty(productRow) && !WebUtility.isEmpty(columnName)) {
			if (!WebUtility.isEmpty(productRow.getCellByName(columnName))) {
				return productRow.getCellByName(columnName).getCellValue().toString();
			}
		}
		return null;
	}
	
	

}