// ILocAidlInterface.aidl
package com.hzbhd.canbus.adapter.location;

// Declare any non-default types here with import statements
import com.hzbhd.canbus.adapter.location.LocationCallback;

interface ILocAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     //void addCallback(LocationCallback locationCallback);
     int getSpeed(int speed);
     void addCallBack(LocationCallback mCallBack);
     void removeCallBack(LocationCallback mCallBack);
}
