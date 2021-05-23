package com.fourglabs.vaccinecallback.notifier;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating notification service
 * 
 * @author balajikatika
 *
 */
public class NotificationServiceFactory {

	private static Map<NotifierEnum, NotificationService> notificationServiceMap;

	private static void init() {
		if (notificationServiceMap == null) {
			synchronized (NotificationServiceFactory.class) {
				if (notificationServiceMap == null) {
					notificationServiceMap = new HashMap<>();
					notificationServiceMap.put(NotifierEnum.TWILIO, new TwilioNotificationServiceImpl());
				}
			}
		}
	}

	static {
		init();
	}

	/**
	 * Get notification service Impl corresponding to {@link NotifierEnum}
	 * 
	 * @param notifier
	 * @return - Implementation of {@link NotificationService}
	 */
	public static NotificationService getNotificationService(NotifierEnum notifier) {

		NotificationService notificationService = null;
		switch (notifier) {
		case TWILIO:
			notificationService = notificationServiceMap.get(notifier);
			break;
		default:
			throw new UnsupportedOperationException("Cannot support" + notifier);
		}
		return notificationService;
	}
}
