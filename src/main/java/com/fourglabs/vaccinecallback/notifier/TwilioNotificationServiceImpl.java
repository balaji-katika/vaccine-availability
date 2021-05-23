package com.fourglabs.vaccinecallback.notifier;

import java.net.URI;

import com.fourglabs.vaccinecallback.constants.AppConfig;
import com.fourglabs.vaccinecallback.log.LogMessage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;

/**
 * Notification Service using TwiLio
 * @author balajikatika
 *
 */
public class TwilioNotificationServiceImpl implements NotificationService {

	public TwilioNotificationServiceImpl() {
		AppConfig appConfig = AppConfig.getInstance();
		Twilio.init(appConfig.getProperty(AppConfig.TWILIO_SID), appConfig.getProperty(AppConfig.TWILIO_TOKEN));
		
	}

	public void notifyWhatsapp(String number) {
		Call call = Call.creator(
                new com.twilio.type.PhoneNumber("+919036492180"),
                new com.twilio.type.PhoneNumber("+919886656337"),
                URI.create("http://demo.twilio.com/docs/voice.xml"))
            .create();

	}

	public void notifyViaCall(String number) {
		try {
		Call call = Call.creator(
                new com.twilio.type.PhoneNumber(number),
                new com.twilio.type.PhoneNumber("+919886656337"),
                URI.create("http://demo.twilio.com/docs/voice.xml"))
            .create();
		} catch (Exception e) {
			LogMessage.logError("Unable to call " + number, e);
		}
		
	}

}
