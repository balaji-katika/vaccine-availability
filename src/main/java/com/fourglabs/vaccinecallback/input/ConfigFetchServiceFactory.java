package com.fourglabs.vaccinecallback.input;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for fetching {@link ConfigFetchService}
 * 
 * @author balajikatika
 *
 */
public class ConfigFetchServiceFactory {

	private static Map<ConfigEnum, ConfigFetchService> configMap;
	static {
		init();
	}

	/**
	 * Get the {@link ConfigFetchService}
	 * 
	 * @param configEnum
	 * @return - Implementation of {@link ConfigFetchService} corresponding to
	 *         {@link ConfigEnum}
	 */
	public static ConfigFetchService getInstance(ConfigEnum configEnum) {
		ConfigFetchService configFetchService = null;

		switch (configEnum) {
		case STATICLIST:
			configFetchService = configMap.get(ConfigEnum.STATICLIST);
			break;
		default:
			throw new UnsupportedOperationException("Cannot Support " + configEnum.name());
		}

		return configFetchService;
	}

	private static void init() {
		configMap = new HashMap<ConfigEnum, ConfigFetchService>();
		configMap.put(ConfigEnum.STATICLIST, new ConfigFetchServiceStaticImpl());
	}

}
