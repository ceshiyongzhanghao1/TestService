package com.example.testservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LaunchReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.wtf("zzz", "BroadcastReceiver onReceive");
        Intent intent1 = new Intent(context , MyService.class);  
        context.startService(intent1);
	}

}
