package com.fourglabs.vaccinecallback.cowin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourglabs.vaccinecallback.constants.AppConfig;
import com.fourglabs.vaccinecallback.dto.ApiOutput;
import com.fourglabs.vaccinecallback.dto.Center;
import com.fourglabs.vaccinecallback.dto.Session;
import com.fourglabs.vaccinecallback.log.LogMessage;

/**
 * Job that checks for availablity of vaccine
 * 
 * @author balajikatika
 *
 */
public class CowinVaccineChecker {

	private static CowinVaccineChecker instance = null;
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * Get Singleton instance of {@link AppConfig}
	 * 
	 * @return - instance
	 */
	public static CowinVaccineChecker getInstance() {

		if (instance == null) {
			synchronized (CowinVaccineChecker.class) {
				if (instance == null) {
					instance = new CowinVaccineChecker();
				}
			}
		}
		return instance;
	}

	public boolean checkAvailabilityByDistrict(int districtId) {
		StringBuffer apiOutputSB = new StringBuffer();

		String date = getTodayDate();
		System.out.println(date);
		try {
			URL url = new URL(
					"https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id="
							+ districtId + "&date=" + date);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("user-agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36");

			if (conn.getResponseCode() != 200) {
				conn.disconnect();
				return false;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output = null;
			apiOutputSB = new StringBuffer();
			while ((output = br.readLine()) != null) {
				apiOutputSB.append(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			LogMessage.logError("", e);

		} catch (IOException e) {

			LogMessage.logError("", e);

		}
		if (isAvailable(apiOutputSB)) {
			return true;
		}

		return false;
	}

	private boolean isAvailable(StringBuffer apiOutputSB) {
		if (apiOutputSB != null && apiOutputSB.length() != 0) {
			ApiOutput apiOutput = null;
			try {
				apiOutput = mapper.readValue(apiOutputSB.toString(), ApiOutput.class);
			} catch (JsonParseException e) {
				LogMessage.logError("", e);
			} catch (JsonMappingException e) {
				LogMessage.logError("", e);
			} catch (IOException e) {
				LogMessage.logError("", e);
			}
			if (apiOutput != null) {

				if (apiOutput != null && apiOutput.getCenters() != null && !apiOutput.getCenters().isEmpty()) {
					for (Center center : apiOutput.getCenters()) {
						if (center.getSessions() != null && !center.getSessions().isEmpty()) {
							for (Session session : center.getSessions()) {
								if (session.getAvailableCapacity() > 0 && session.getMinAgeLimit() < 45
										&& session.getAvailableCapacityDose1() > 0) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private String getTodayDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Using today's date
		return sdf.format(c.getTime());
	}

}
