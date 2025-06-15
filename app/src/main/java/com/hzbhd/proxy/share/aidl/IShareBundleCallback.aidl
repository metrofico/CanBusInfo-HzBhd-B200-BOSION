package com.hzbhd.proxy.share.aidl;
import android.os.Bundle;

// Declare any non-default types here with import statements

interface IShareBundleCallback {
    void onBundle(inout Bundle value,int from);
}