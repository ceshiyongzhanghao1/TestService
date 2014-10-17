package com.example.testservice.location;

import com.baidu.location.BDLocation;

import android.util.Log;

public class MyLocationCallback {

	public String mCommandId;

	public void successCallback(BDLocation location) {
		String x = String.valueOf(location.getLongitude());
		String y = String.valueOf(location.getLatitude());
		if (x != null && y != null) {

		} else {
			x = "0.0";
			y = "0.0";
		}
//		DataUtil.saveLocationToDB(x, y);
//		if (mCommandId != null
//				&& !mCommandId.equals(FloatingWindowService.COMMAND_ID_EMPTY)
//				&& FloatingWindowService.getGlobalContext() != null) {
////			ServiceUtil.pushLocationMessage(
////					FloatingWindowService.getGlobalContext(), mCommandId);
//		}else{
////			ServiceUtil.pushLocationMessage(
////					FloatingWindowService.getGlobalContext(), FloatingWindowService.COMMAND_ID_EMPTY);
//		}
		Log.wtf("zzz", "saved Location to DB." + x + " " + y);
	}

	public void failtureCallback(BDLocation location) {
//		DataUtil.saveLocationToDB("0.0", "0.0");
//		if (mCommandId != null
//				&& !mCommandId.equals(FloatingWindowService.COMMAND_ID_EMPTY)
//				&& FloatingWindowService.getGlobalContext() != null) {
//			ServiceUtil.pushLocationMessage(
//					FloatingWindowService.getGlobalContext(), mCommandId);
//		}
		Log.i("zzz", "get Location failed.");
	}
}
