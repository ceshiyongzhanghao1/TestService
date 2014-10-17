package com.example.testservice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class NoUseActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.surfaceview);
		Toast.makeText(getApplicationContext(), "NoUseActivity onCreate", Toast.LENGTH_SHORT).show();
		Log.wtf("zzz", "Activity onCreate");
		this.finish();
	}
}
