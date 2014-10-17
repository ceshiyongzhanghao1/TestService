package com.example.testservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceivedListener {

	public void doReceive(BroadcastReceiver receiver, Context context, Intent intent){
		Vibrator vib = (Vibrator)context.getSystemService(Service.VIBRATOR_SERVICE);  
		vib.vibrate(1200); 
		StringBuilder sb = new StringBuilder();
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			Object[] pdus = (Object[]) bundle.get("pdus");
			SmsMessage[] msg = new SmsMessage[pdus.length];
			for (int i = 0; i < pdus.length; i++) {
				msg[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
			}
			for (SmsMessage currMsg : msg) {
				sb.append("From:");
				sb.append(currMsg.getDisplayOriginatingAddress());
				sb.append("\nMessage:");
				sb.append(currMsg.getDisplayMessageBody());
			}
		}
		Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
		Log.wtf("zzz", sb.toString());
		receiver.abortBroadcast();
	}
}
