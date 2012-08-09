This library provides methods for verifying addresses using SmartyStreets' [LiveAddress API](http://smartystreets.com/kb/liveaddress-api/rest-endpoint)

example usage:

	JSONArray address = new JSONArray();
	address.put(JLiveAddressService.STREET, "1 Infinite Loop");
	address.put(JLiveAddressService.CITY, "Cupertino");
	address.put(JLiveAddressService.STATE, "CA");
 
 	JLiveAddressService service = new JLiveAddressService("<your auth token>");
	List<JLiveAddressResult> results = service.verifyAddress(address);	

Requires [Json-lib](http://json-lib.sourceforge.net) 2.2.2 or higher in your projects build path.