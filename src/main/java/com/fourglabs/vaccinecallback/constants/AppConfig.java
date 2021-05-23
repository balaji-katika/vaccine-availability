package com.fourglabs.vaccinecallback.constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.fourglabs.vaccinecallback.log.LogMessage;

/**
 * Application Configuration
 * 
 * @author balajikatika
 *
 */
public class AppConfig {
	private static final String inputFileName = "appconfig.properties";
	public static final String TWILIO_SID = "ACCOUNT_SID";
	public static final String TWILIO_TOKEN = "AUTH_TOKEN";
	private Properties props;
	private static AppConfig instance = null;

	/**
	 * Private constructor for {@link AppConfig}
	 */
	private AppConfig() {
		props = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(inputFileName);
		if (inputStream != null) {
			try {
				props.load(inputStream);
			} catch (IOException ioException) {
				LogMessage.logError(String.format("Exception while loading %s", inputStream), ioException);
			}
		} else {
			LogMessage.logError(String.format("Unable to load %s", inputStream));
		}
	}

	/**
	 * Get Singleton instance of {@link AppConfig}
	 * 
	 * @return - instance
	 */
	public static AppConfig getInstance() {

		if (instance == null) {
			synchronized (AppConfig.class) {
				if (instance == null) {
					instance = new AppConfig();
				}
			}
		}
		return instance;
	}

	/**
	 * Get the property
	 * @param propName - Name of the property 
	 * @return - Value
	 */
	public String getProperty(String propName) {
		return props.getProperty(propName);
	}

}
