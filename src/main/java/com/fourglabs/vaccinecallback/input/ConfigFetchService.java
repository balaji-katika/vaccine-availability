package com.fourglabs.vaccinecallback.input;

import java.util.List;

public interface ConfigFetchService {
	public List<Config> loadByDistrict();
}
