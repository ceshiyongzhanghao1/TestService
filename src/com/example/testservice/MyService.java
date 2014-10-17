package com.example.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("service onBind");
		Log.wtf("zzz", "service onBind");
		return null;
	}

	@Override
	public void onCreate() {
		System.out.println("service create");
		Log.wtf("zzz", "service create");
	}

}
