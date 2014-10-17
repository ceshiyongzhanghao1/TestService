package com.example.testservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.os.Vibrator;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class InstantMessenger extends AccessibilityService {
	boolean isInit = false;

	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
			// Do something, eg getting packagename
//			final String packagename = String.valueOf(event.getPackageName());
//			
//
//			Toast.makeText(this, "onAccessibilityEvent "+event.getText().toString(), Toast.LENGTH_LONG).show();
//			Log.wtf("zzz", event.getText().toString());
//
//            Vibrator vib = (Vibrator)this.getSystemService(Service.VIBRATOR_SERVICE);  
//            vib.vibrate(500);  
//			
			
		}
	}

//	@Override
//	public void onCreate() {
//		onServiceConnected();
//	}
	
	@Override
	protected void onServiceConnected() {
		if (isInit) {
			return;
		}
		AccessibilityServiceInfo info = new AccessibilityServiceInfo();
		info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
		info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
		setServiceInfo(info);
		isInit = true;
	}

	@Override
	public void onInterrupt() {
		isInit = false;
	}
}