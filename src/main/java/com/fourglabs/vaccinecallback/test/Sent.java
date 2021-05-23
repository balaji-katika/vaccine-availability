package com.fourglabs.vaccinecallback.test;

import java.net.URI;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;

public class Sent {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC97588ff42e74694c79dc7630028aadc9";
    public static final String AUTH_TOKEN = "4bba72001d93600d8df6c4d1edee2e58";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Call call = Call.creator(
                new com.twilio.type.PhoneNumber("+919036492180"),
                new com.twilio.type.PhoneNumber("+919886656337"),
                URI.create("http://demo.twilio.com/docs/voice.xml"))
            .create();

        System.out.println(call.getSid());
    }
}