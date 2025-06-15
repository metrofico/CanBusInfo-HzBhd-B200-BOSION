package com.hzbhd.canbus.car._283;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import java.util.Calendar;
import java.util.Iterator;


public class GPSTimeManager {
    private static GPSTimeManager mGPSTimeManager;
    private LocationManager locationManager;
    private Context mContext;
    GpsStatus.Listener mGpsStatus = new GpsStatus.Listener() { // from class: com.hzbhd.canbus.car._283.GPSTimeManager.1
        @Override // android.location.GpsStatus.Listener
        public void onGpsStatusChanged(int i) {
            if (i == 1) {
                Log.d("scy", "------->定位启动");
                return;
            }
            if (i == 2) {
                Log.d("scy", "------->定位结束");
                return;
            }
            if (i == 3) {
                Log.d("scy", "------->第一次定位");
                return;
            }
            if (i != 4) {
                return;
            }
            GpsStatus gpsStatus = GPSTimeManager.this.locationManager.getGpsStatus(null);
            int maxSatellites = gpsStatus.getMaxSatellites();
            Iterator<GpsSatellite> it = gpsStatus.getSatellites().iterator();
            int i2 = 0;
            while (it.hasNext() && i2 <= maxSatellites) {
                if (it.next().getSnr() != 0.0f) {
                    i2++;
                }
            }
            if (ActivityCompat.checkSelfPermission(GPSTimeManager.this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(GPSTimeManager.this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                Log.d("scy", "卫星状态改变------->" + i2);
            }
        }
    };
    LocationListener mLocationListener = new LocationListener() { // from class: com.hzbhd.canbus.car._283.GPSTimeManager.2
        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            Log.d("scy", "onLocationChanged--GPS获取定位第一次获取到时间----->" + location.getTime());
            long time = location.getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            int i = calendar.get(1);
            int i2 = calendar.get(2) + 1;
            int i3 = calendar.get(5);
            int i4 = calendar.get(11);
            int i5 = calendar.get(12);
            int i6 = calendar.get(13);
            if (GPSTimeManager.this.mOnGPSTimeCallBack != null) {
                GPSTimeManager.this.mOnGPSTimeCallBack.onTimeCallBack(i, i2, i3, i4, i5, i6);
            }
            GPSTimeManager.this.unregister();
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
            Log.d("scy", "onStatusChanged------->" + str + "<----->" + i);
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
            Log.d("scy", "onProviderDisabled------->" + str);
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
            Log.d("scy", "onProviderDisabled------->" + str);
        }
    };
    private OnGPSTimeCallBack mOnGPSTimeCallBack;

    public interface OnGPSTimeCallBack {
        void onTimeCallBack(int i, int i2, int i3, int i4, int i5, int i6);
    }

    public static GPSTimeManager getInstance() {
        if (mGPSTimeManager == null) {
            mGPSTimeManager = new GPSTimeManager();
        }
        return mGPSTimeManager;
    }

    public void start(Context context) {
        this.mContext = context;
        this.locationManager = (LocationManager) context.getSystemService("location");
        if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.locationManager.addGpsStatusListener(this.mGpsStatus);
            this.locationManager.requestLocationUpdates("gps", 1000L, 1.0f, this.mLocationListener);
        }
    }

    public void unregister() {
        try {
            this.locationManager.removeGpsStatusListener(this.mGpsStatus);
            this.locationManager.removeUpdates(this.mLocationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnGPSTimeCallBack(OnGPSTimeCallBack onGPSTimeCallBack) {
        this.mOnGPSTimeCallBack = onGPSTimeCallBack;
    }
}
