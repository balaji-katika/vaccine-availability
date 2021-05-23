package com.fourglabs.vaccinecallback.scheduler;

import com.fourglabs.vaccinecallback.cowin.CowinVaccineChecker;
import com.fourglabs.vaccinecallback.input.Config;
import com.fourglabs.vaccinecallback.input.ConfigEnum;
import com.fourglabs.vaccinecallback.input.ConfigFetchService;
import com.fourglabs.vaccinecallback.input.ConfigFetchServiceFactory;
import com.fourglabs.vaccinecallback.notifier.NotificationService;
import com.fourglabs.vaccinecallback.notifier.NotificationServiceFactory;
import com.fourglabs.vaccinecallback.notifier.NotifierEnum;

/**
 * Service to check for given pin code and notify
 * 
 * @author balajikatika
 *
 */
public class VaccineCallbackJob {

	public void execute() {
		boolean available = false;
		CowinVaccineChecker vc = CowinVaccineChecker.getInstance();
		NotificationService ns = NotificationServiceFactory.getNotificationService(NotifierEnum.TWILIO);
		ConfigFetchService cfs = ConfigFetchServiceFactory.getInstance(ConfigEnum.STATICLIST);

		for (Config config : cfs.loadByDistrict()) {
			available = vc.checkAvailabilityByDistrict(config.getDistrictId());
			if (available) {
				// Notify
				for (String number : config.getPhoneNumbers()) {
					ns.notifyViaCall(number);
				}
			}
		}
	}

	/**
	 * Perform the check for given district Id
	 * @param districtId
	 */
	public void execute(int districtId) {
		boolean available = false;
		CowinVaccineChecker vc = CowinVaccineChecker.getInstance();
		NotificationService ns = NotificationServiceFactory.getNotificationService(NotifierEnum.TWILIO);

		available = vc.checkAvailabilityByDistrict(districtId);
		if (available) {
			// Notify
			ns.notifyViaCall("+919036492180");
		}
	}
}
