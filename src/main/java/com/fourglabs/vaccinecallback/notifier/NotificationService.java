package com.fourglabs.vaccinecallback.notifier;

public interface NotificationService {
	/**
	 * Notify via Whatsapp
	 * @param number - Contact number
	 */
	public void notifyWhatsapp(String number);
	
	public void notifyViaCall(String number);
}
