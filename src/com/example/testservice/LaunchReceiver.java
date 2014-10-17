package com.example.testservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class LaunchReceiver extends BroadcastReceiver {

	public final static String ONSTART = "android.intent.action.BOOT_COMPLETED";
	public final static String TIMETICK = "android.intent.action.TIME_TICK";
	public final static String OPENSCREEN = "android.intent.action.USER_PRESENT";
	public final static String RECEIVE_SMS = "android.provider.Telephony.SMS_RECEIVED";
	public boolean isRegisteredTimeStick = false;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.wtf("zzz", "BroadcastReceiver onReceive:" + intent.getAction());
		
		if(ONSTART.equals(intent.getAction()) || OPENSCREEN.equals(intent.getAction()) ){
			if(!isRegisteredTimeStick){
				Log.wtf("zzz", "checked::" + intent.getAction());

				Log.wtf("zzz", "going to start service");
				
				Intent intent2 = new Intent();
				intent2.setClass(context, MyService.class);
				context.startService(intent2);
				Log.wtf("zzz", "service started");

				
//				Intent intent3 = new Intent();
//				intent3.setClass(context, InstantMessenger.class);
//				context.startService(intent3);

			    IntentFilter timeTickFilter = new IntentFilter();
			    timeTickFilter.addAction(TIMETICK);
				context.getApplicationContext().registerReceiver(this, timeTickFilter);
				isRegisteredTimeStick = true;
			}
		}
		
		if(RECEIVE_SMS.equals(intent.getAction())){
            Log.wtf("zzz", "LaunchReceiver 收到短信发来的消息"); 
            new SMSReceivedListener().doReceive(this, context, intent);
		}
		
		
		
		
//        Intent intent1 = new Intent(context , MyService.class);  
//        context.startService(intent1);
	}

}
