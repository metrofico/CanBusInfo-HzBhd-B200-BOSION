package com.hzbhd.canbus.fragment;

import android.app.Fragment;
import android.view.View;
import com.hzbhd.canbus.util.CommUtil;

/* loaded from: classes2.dex */
public abstract class BaseFragment extends Fragment {
    protected void showOrHide(View view, boolean z) {
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }

    protected String getStringByName(String str) {
        return getString(CommUtil.getStrIdByResId(getActivity(), str));
    }
}
