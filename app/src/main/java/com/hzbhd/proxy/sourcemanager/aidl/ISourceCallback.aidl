// ISourceCallback.aidl
package com.hzbhd.proxy.sourcemanager.aidl;

// Declare any non-default types here with import statements

interface ISourceCallback {
    void onAction(int action, in Bundle bundle);
}