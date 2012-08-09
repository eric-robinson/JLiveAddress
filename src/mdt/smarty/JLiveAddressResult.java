package mdt.smarty;

import net.sf.json.JSONObject;

/**
 * This class provides key definitions and cover methods for accessing
 * LiveAddress results JSON.
 * 
 * @author Eric Robinson
 */
public class JLiveAddressResult {
	public static final String INPUT_INDEX = "input_index";
	public static final String CANDIDATE_INDEX = "candidate_index";
	public static final String ADDRESSEE = "addressee";
	public static final String DELIVERY_LINE_1 = "delivery_line_1";
	public static final String DELIVERY_LINE_2 = "delivery_line_2";
	public static final String LAST_LINE = "last_line";
	public static final String DELIVERY_POINT_BARCODE = "delivery_point_barcode";
	public static final String COMPONENTS = "components";
	public static final String METADATA = "metadata";
	public static final String ANALYSIS = "analysis";

	public static final String URBANIZATION = "urbanization";
	public static final String PRIMARY_NUMBER = "primary_number";
	public static final String STREET_NAME = "street_name";
	public static final String STREET_PREDIRECTION = "street_predirection";
	public static final String STREET_POSTDIRECTION = "street_postdirection";
	public static final String STREET_SUFFIX = "street_suffix";
	public static final String SECONDARY_NUMBER = "secondary_number";
	public static final String SECONDARY_DESIGNATOR = "secondary_designator";
	public static final String PMB_DESIGNATOR = "pmb_designator";
	public static final String PMB_NUMBER = "pmb_number";
	public static final String CITY_NAME = "city_name";
	public static final String STATE_ABBREVIATION = "state_abbreviation";
	public static final String ZIPCODE = "zipcode";
	public static final String PLUS4_CODE = "plus4_code";
	public static final String DELIVERY_POINT = "delivery_point";
	public static final String DELIVERY_POINT_CHECK_DIGIT = "delivery_point_check_digit";

	public static final String RECORD_TYPE = "record_type";
	public static final String COUNTRY_FIPS = "country_fips";
	public static final String COUNTRY_NAME = "country_name";
	public static final String CARRIER_ROUTE = "carrier_route";
	public static final String CONGRESSIONAL_DISTRICT = "congressional_district";
	public static final String BUILDING_DEFAULT_INDICATOR = "building_default_indicator";
	public static final String RDI = "rdi";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String PRECISION = "precision";

	public static final String DPV_MATCH_CODE = "dpv_match_code";
	public static final String DPV_FOOTNOTES = "dpv_footnotes";
	public static final String DPV_CMRA = "dpv_cmra";
	public static final String DPV_VACANT = "dpv_vacant";
	public static final String ACTIVE = "active";
	public static final String EWS_MATCH = "ews_match";
	public static final String FOOTNOTES = "footnotes";
	public static final String LACSLINK_CODE = "lacslink_code";
	public static final String LACSLINK_INDICATOR = "lacslink_indicator";

	JSONObject _addressJSON;

	/**
	 * Create a JLiveAddressResult from the provided json
	 * 
	 * @param addressJSON
	 */
	public JLiveAddressResult(JSONObject addressJSON) {
		_addressJSON = addressJSON;
	}

	/**
	 * get a value from the address JSON
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return _addressJSON.optString(key);
	}

	/**
	 * utility method to get a value from the results "components" map
	 * 
	 * @param key
	 * @return
	 */
	public String getComponent(String key) {
		return _addressJSON.optJSONObject(COMPONENTS).getString(key);
	}

	/**
	 * utility method to get a value from the results "metadata" map
	 * 
	 * @param key
	 * @return
	 */
	public Object getMetadata(String key) {
		return _addressJSON.optJSONObject(METADATA).get(key);
	}

	/**
	 * utility method to get a value from the results "analysis" map
	 * 
	 * @param key
	 * @return
	 */
	public String getAnalysis(String key) {
		return _addressJSON.optJSONObject(ANALYSIS).getString(key);
	}

	/**
	 * get the raw json
	 * 
	 * @return
	 */
	public JSONObject getAddressJSON() {
		return _addressJSON;
	}

	/**
	 * set the raw json
	 * 
	 * @param addressJSON
	 */
	public void setAddressJSON(JSONObject addressJSON) {
		_addressJSON = addressJSON;
	}
}
