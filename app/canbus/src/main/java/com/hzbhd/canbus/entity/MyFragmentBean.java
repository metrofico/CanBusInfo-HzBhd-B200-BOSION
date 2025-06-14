package com.hzbhd.canbus.entity;

import android.app.Fragment;

/* loaded from: classes2.dex */
public class MyFragmentBean {
    private Fragment fragment;
    private String title;

    public MyFragmentBean(Fragment fragment, String str) {
        this.fragment = fragment;
        this.title = str;
    }

    public Fragment getFragment() {
        return this.fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
