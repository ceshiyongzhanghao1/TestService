package com.example.testservice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class NoUseActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.wtf("zzz", "Activity onCreate");
	}
}
