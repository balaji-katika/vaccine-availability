package com.fourglabs.vaccinecallback.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigFetchServiceStaticImpl implements ConfigFetchService {
	List<Config> configs;

	public ConfigFetchServiceStaticImpl() {
		configs = new ArrayList<Config>();
		Config c = new Config();
		c.setDistrictId(7);
		c.setPhoneNumbers(Arrays.asList("+919036492180"));
		configs.add(c);
	}

	public List<Config> loadByDistrict() {
		return configs;
	}

}
