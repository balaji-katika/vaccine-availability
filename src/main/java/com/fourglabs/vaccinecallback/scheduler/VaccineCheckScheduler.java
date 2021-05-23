package com.fourglabs.vaccinecallback.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fourglabs.vaccinecallback.RESTAPIController;

/**
 * Scheduler for Vaccine Check to run every 5 mins
 * 
 * @author balajikatika
 *
 */
@Component
public class VaccineCheckScheduler {
	VaccineCallbackJob callbackJob = new VaccineCallbackJob();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	@Scheduled(fixedRate = 300000)
	public void cronJobSch() {
		LocalDateTime now = LocalDateTime.now();
		System.out.println();
		try {

			callbackJob.execute();
			RESTAPIController.latestTime = "Checked success at " + dtf.format(now);
		} catch (Exception e) {
			RESTAPIController.latestTime = "Exception at " + dtf.format(now);
			// Suppress Exception
		}
	}
}
