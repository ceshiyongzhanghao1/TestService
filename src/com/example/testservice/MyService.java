package com.example.testservice;

import com.example.testservice.location.MyLocationService;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Vibrator;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	public boolean isRegisteredSMS = false;
	

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("service onBind");
		Toast.makeText(getApplicationContext(), "service onBind", Toast.LENGTH_SHORT).show();
		Log.wtf("zzz", "service onBind");
		return null;
	}

	@Override
	public void onCreate() {
		System.out.println("service onCreate");
		Log.wtf("zzz", "service onCreate");
		Toast.makeText(getApplicationContext(), "service onCreate", Toast.LENGTH_SHORT).show();
		
		if(!isRegisteredSMS){
			Vibrator vib = (Vibrator)this.getSystemService(Service.VIBRATOR_SERVICE);  
			vib.vibrate(1200); 
//	        IntentFilter smsFilter = new IntentFilter(LaunchReceiver.RECEIVE_SMS);  
//	        smsFilter.setPriority(2147483647);
//	        SmsReceiver smsReceiver = new SmsReceiver();
//	        getApplicationContext().registerReceiver(smsReceiver, smsFilter);
//	        isRegisteredSMS = true;
		}
		
		
		Handler handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					break;
				case 1:
					new MyLocationService().requestLocation(MyService.this);
//					Runnable r = new Runnable() {
//						@Override
//						public void run() {
//							new MyLocationService().requestLocation(MyService.this);
//						}
//					};
//					new Thread(r).start();
					break;
				default:
					break;
				}
			}
		};
		
		handler.sendEmptyMessage(1);
		
	}
	
	
	private class SmsReceiver extends BroadcastReceiver{  
		  
        @Override  
        public void onReceive(Context context, Intent intent) {  
            if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){    
                Log.wtf("zzz", "MyService SmsReceiver 收到短信发来的消息");    
                new SMSReceivedListener().doReceive(this, context, intent);  
//                Vibrator vib = (Vibrator)MyService.this.getSystemService(Service.VIBRATOR_SERVICE);  
//                vib.vibrate(500);
//                abortBroadcast();  
            }    
        }  
          
    }  

}
