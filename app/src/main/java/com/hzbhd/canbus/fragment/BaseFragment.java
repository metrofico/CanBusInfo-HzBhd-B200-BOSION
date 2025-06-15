package com.hzbhd.canbus.fragment;

import android.app.Fragment;
import android.view.View;

import com.hzbhd.canbus.util.CommUtil;

/* loaded from: classes2.dex */
public abstract class BaseFragment extends Fragment {
    protected void showOrHide(View view, boolean z) {
        if (z) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }

    protected String getStringByName(String str) {
        return getString(CommUtil.getStrIdByResId(getActivity(), str));
    }
}
