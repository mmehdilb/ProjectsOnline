package net.wavemark.cims.datamanagers;

import java.util.List;

import net.wavemark.cims.utility.DataRow;

public class HSetupAddProductsDM extends DataManager {

	public static final String COL_MFR_ID = "MFR_ID";

	public static final String COL_MFR_NAME = "MFR_NAME";

	public static final String COL_PRODUCT_ID = "PRODUCT_ID";

	public static final String COL_DIVISION_ID = "DIVISION_ID";

	public static final String COL_PRODGROUP_ID = "PRODGROUP_ID";

	public static final String COL_PRODUCT_NAME = "PRODUCT_NAME";

	public static final String COL_ORDERING_PRODUCT_UOM = "ORDERING_PRODUCT_UOM";

	public static final String COL_EMSCHARGENUMBER = "EMSCHARGENUMBER";

	public static final String COL_GLACCOUNT_MAJOR = "GLACCOUNT_MAJOR";

	public static final String COL_GLACCOUNT_MINOR = "GLACCOUNT_MINOR";

	public static final String COL_HDS_ITEMNUMBER = "HDS_ITEMNUMBER";

	public static final String COL_ORDERING_ENDPOINTID = "ORDERING_ENDPOINTID";

	public static final String COL_MMSSUPPLY_LOCATION_ID = "MMSSUPPLY_LOCATION_ID";

	public static final String COL_BIOLOGICAL_IMPLANT_FLG = "BIOLOGICAL_IMPLANT_FLG";

	public static final String COL_COMMENTS = "COMMENTS";

	public static final String COL_LOCATION = "LOCATION";

	public static final String COL_ITEM_NUMBER = "ITEM_NUMBER";

	public static final String COL_PRODUCT_CATEGORY = "PRODUCT_CATEGORY";

	public static final String COL_DISTRIBUTORID = "DISTRIBUTORID";

	public static final String COL_MMSVENDORID = "MMSVENDORID";

	public static final String COL_SUPPLIER_TYPE = "SUPPLIER_TYPE";

	public static final String COL_IS_IMPLANTABLE = "IS_IMPLANTABLE";

	public static final String COL_IS_CONSIGNED = "IS_CONSIGNED";

	public static final String COL_COST = "COST";

	public HSetupAddProductsDM() {
		super();
	}

	// public List<DataRow> getEnterpriseProducts() throws Exception, Exception
	// {
	// List<DataRow> suggestedProducts = null;
	// try {
	// if (suggestedProducts == null) {
	// suggestedProducts = super.getData("query");
	// }
	// } catch (Exception e) {
	// throw e;
	// }
	// return suggestedProducts;
	// }

	public List<DataRow> getEnterpriseProducts() throws Exception {
		try {
			return super.getData("query");
		} catch (Exception e) {
			throw e;
		}
	}
}