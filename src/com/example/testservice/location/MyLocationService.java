package com.example.testservice.location;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

public class MyLocationService {
	public static boolean isLocating = false;
	
	public LocationClient mLocationClient;
	public GeofenceClient mGeofenceClient;
	private MyLocationListener mMyLocationListener;
	private boolean mLocationInit;
	private boolean mLocationSequency;
	private boolean mIsNeedAddress;
	private String mCoordType;
	private boolean mIsNeedDirection;
	public static MyLocationCallback myLocationCallback;

	public static MyLocationCallback getMyLocationCallback() {
		return myLocationCallback;
	}

	public static void setMyLocationCallback(MyLocationCallback mlc) {
		myLocationCallback = mlc;
	}
	
	public void requestLocation(Context t) {
		// getLocationParams();

		Log.wtf("zzz", "MyLocationService is OnCreate.");
		mLocationClient = new LocationClient(t);
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		setMyLocationCallback(new MyLocationCallback());

		// mGeofenceClient = new GeofenceClient(this);
		// mNotifyLister = new NotifyLister();
		// mVibrator
		// =(Vibrator)context.getSystemService(Service.VIBRATOR_SERVICE);
		mLocationInit = true;

		setLocationOption();
		// 开始定位
		if (mLocationInit && !isLocating) {
			mLocationClient.start();
		}

		Log.wtf("zzz", "Locating...");
		isLocating = true;

		if (/* !mLocationSequency && */mLocationClient.isStarted()) {
			// 单次请求定位
			mLocationClient.requestLocation();
		}

		isLocating = true;
		// Intent i = new Intent(MainActivity.this,
		// LocationResultActivity.class);
		// startActivity(i);
	}

	// 设置Option
	private void setLocationOption() {
		// mLocationMode = LocationMode.Hight_Accuracy;
		mIsNeedAddress = true;
		mCoordType = "bd09ll";
		mIsNeedDirection = true;
		mLocationSequency = false;
		try {
			LocationClientOption option = new LocationClientOption();
			option.setLocationMode(LocationMode.Hight_Accuracy);
			option.setCoorType(mCoordType);
			// option.setScanSpan(mScanSpan);
			option.setNeedDeviceDirect(mIsNeedDirection);
			option.setIsNeedAddress(mIsNeedAddress);
			mLocationClient.setLocOption(option);
			mLocationInit = true;
		} catch (Exception e) {
			e.printStackTrace();
			mLocationInit = false;
		}
	}

	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// mVibrator.vibrate(1000);
			// Receive Location
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\ndirection : ");
				sb.append(location.getDirection());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				// 运营商信息
				sb.append("\noperationers : ");
				sb.append(location.getOperators());
			}
			Log.wtf("zzz", sb.toString());

			mLocationClient.stop();

			Log.wtf("zzz", "Locate ove.");
			isLocating = false;

			switch (location.getLocType()) {
			case 61:
				// case 65:
			case 66:
				// case 67:
			case 68:
			case 161:
				myLocationCallback.successCallback(location);
				break;
			default:
				myLocationCallback.failtureCallback(location);
				break;
			}
			isLocating = false;

		}

//		@Override
//		public void onReceivePoi(BDLocation arg0) {
//			Log.i("zzz", "onReceivePoi");
//			FloatingWindowService.isLocating = false;
//
//		}
	}
}
