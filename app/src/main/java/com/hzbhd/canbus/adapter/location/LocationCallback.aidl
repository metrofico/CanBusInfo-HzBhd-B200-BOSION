package com.hzbhd.canbus.adapter.location;

/**
 * Created by poulwalker on 2020/4/13.
 */
interface LocationCallback {
    void getSpeed(int speed);
    void getMaxSatelliteCount(int count);
    void getLoc(double Longitude,double Latitude);
    void getBear(float bear);
    void getBearAndAltitude(float bear,double altitude);
    void getAltitude(double altitude);
}
