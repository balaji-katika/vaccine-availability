package com.fourglabs.vaccinecallback.log;

import java.io.IOException;

/**
 * Decorator/Integration for logging frameworkds
 * 
 * @author balajikatika
 *
 */
public class LogMessage {

	/**
	 * Log error message
	 * 
	 * @param message
	 */
	public static void logError(String message) {
		System.err.println(message);

	}

	/**
	 * Log error message and exception
	 * 
	 * @param message
	 * @param throwable
	 */
	public static void logError(String message, Throwable throwable) {
		System.err.println(message);
		throwable.printStackTrace();

	}

}
