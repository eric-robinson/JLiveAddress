This library provides methods for verifying addresses using SmartyStreets' [LiveAddress API](http://smartystreets.com/kb/liveaddress-api/rest-endpoint)

example usage:
 
	JSONArray address = new JSONArray();
	address.put(JLiveAddressService.STREET, "1 Infinite Loop");
	address.put(JLiveAddressService.CITY, "Cupertino");
	address.put(JLiveAddressService.STATE, "CA");
 
	List results = new JLiveAddressService.verifyAddress(address);

