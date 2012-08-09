package mdt.smarty;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * This class provides methods for verifying addresses using SmartyStreets'
 * LiveAddress API. http://smartystreets.com/kb/liveaddress-api/rest-endpoint
 * example usage:
 * 
 * JSONArray address = new JSONArray();
 * address.put(JLiveAddressService.STREET, "1 Infinite Loop");
 * address.put(JLiveAddressService.CITY, "Cupertino");
 * address.put(JLiveAddressService.STATE, "CA");
 * 
 * List results = new JLiveAddressService.verifyAddress(address);
 * 
 * @author Eric Robinson
 */
public class JLiveAddressService {

	private String _authToken;
	private String _requestURL;

	public static final String STREET = "street";
	public static final String STREET2 = "street2";
	public static final String SECONDARY = "secondary";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String ZIP_CODE = "zipcode";
	public static final String LAST_LINE = "lastline";
	public static final String ADDRESSEE = "addressee";
	public static final String URBANIZATION = "urbanization";
	public static final String CALLBACK = "callback";
	public static final String CANDIDATES = "candidates";

	/**
	 * Create a JLiveAddressService with the provided authToken
	 * 
	 * @param authToken
	 *          - Your SmartyStreets account auth token, available at
	 *          https://smartystreets.com/account/keys
	 */
	public JLiveAddressService(String authToken) {
		_authToken = authToken;
		_requestURL = "https://api.qualifiedaddress.com/street-address/?auth-token=" + _authToken;
	}

	/**
	 * utility method to create address JSON from a Map
	 * 
	 * @param keysAndValues
	 * @return
	 */
	public JSONObject addressJSON(Map<String, String> keysAndValues) {
		JSONObject address = new JSONObject();

		for (String key : keysAndValues.keySet()) {
			String value = keysAndValues.get(key);
			address.put(key, value);
		}

		return address;
	}

	/**
	 * Verify a single address described by the provided JSONObject. see
	 * http://smartystreets.com/kb/liveaddress-api/rest-endpoint
	 * 
	 * @param address
	 *          - a JSONOBject which must contain a JLiveAddressService.STREET
	 *          ("street") key
	 * @return a List of JLiveAddressResult objects with the verification results
	 *         results
	 * @throws IllegalArgumentException
	 *           - thrown when to street key is present
	 */
	public List<JLiveAddressResult> verifyAddress(JSONObject address) throws IllegalArgumentException {
		JSONArray array = new JSONArray();
		array.add(address);

		List<JLiveAddressResult> addresses = verifyAddresses(array);
		return addresses;

	}

	/**
	 * Verify a set of addresses
	 * 
	 * @param addresses
	 *          - a JSONArray where each JSONObject must contain a
	 *          JLiveAddressService.STREET ("street") key
	 * @returna List of JLiveAddressResult objects with the verification results
	 *          results
	 * @throws IllegalArgumentException
	 *           - thrown when to street key is present
	 */
	public List<JLiveAddressResult> verifyAddresses(JSONArray addresses) throws IllegalArgumentException {
		for (int i = 0; i < addresses.size(); i++) {
			JSONObject addr = (JSONObject) addresses.get(i);
			if (!addr.containsKey(STREET)) {
				throw new IllegalArgumentException("All addresses must contain a \"street\" key");
			}
		}

		System.out.println("verifying " + addresses.size() + " addresses.\nrequest json is " + addresses);

		String response = "";
		String req = addresses.toString();
		int len = req.length();

		try {
			URL u = new URL(_requestURL);

			try {
				// Establish connection, stream our JSON string, and close the
				// connection.
				HttpsURLConnection urlConn = (HttpsURLConnection) u.openConnection();
				urlConn.setDoInput(true);
				urlConn.setDoOutput(true);
				urlConn.setUseCaches(false);
				urlConn.setRequestMethod("POST");
				urlConn.setRequestProperty("Content-Length", Integer.toString(len));

				DataOutputStream outgoing = new DataOutputStream(urlConn.getOutputStream());
				String content = req;
				outgoing.writeBytes(content);
				outgoing.flush();
				outgoing.close();

				// Save the response (a JSON string)
				BufferedReader incoming = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				String str;
				while ((str = incoming.readLine()) != null) {
					response += str;
				}
				incoming.close();
			}
			catch (IOException e) {
				System.out.println("IO Exception Error: " + e.toString());
			}
		}
		catch (MalformedURLException m) {
			System.out.println("Malformed URL Exception Error: " + m.toString());
		}

		JSONArray json = JSONArray.fromObject(response);
		System.out.println("results are: " + json);

		ArrayList<JLiveAddressResult> results = new ArrayList<JLiveAddressResult>();
		for (int i = 0; i < json.size(); i++) {
			JSONObject obj = json.getJSONObject(i);
			results.add(new JLiveAddressResult(obj));
		}

		return results;
	}
}
