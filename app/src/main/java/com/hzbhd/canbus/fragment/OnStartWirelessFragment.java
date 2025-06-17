package com.hzbhd.canbus.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;

/* loaded from: classes2.dex */
public class OnStartWirelessFragment extends BaseFragment {
    private RelativeLayout mInfoRl;
    private TextView mInfoTv;
    private RelativeLayout mPasswordRl;
    private RelativeLayout mPointRl;
    private TextView mPointTv;
    private TextView mPswTv;
    private View mView;

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.fragment_on_star_wireless, viewGroup, false);
            findViews();
            initViews();
        }
        ViewGroup viewGroup2 = (ViewGroup) this.mView.getParent();
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.mView);
        }
        return this.mView;
    }

    private void findViews() {
        this.mInfoTv = (TextView) this.mView.findViewById(R.id.tv_info);
        this.mPointTv = (TextView) this.mView.findViewById(R.id.tv_point);
        this.mPswTv = (TextView) this.mView.findViewById(R.id.tv_psw);
        this.mInfoRl = (RelativeLayout) this.mView.findViewById(R.id.rl_info);
        this.mPointRl = (RelativeLayout) this.mView.findViewById(R.id.rl_point);
        this.mPasswordRl = (RelativeLayout) this.mView.findViewById(R.id.rl_psw);
    }

    private void initViews() {
        refreshUi(null);
    }

    public void refreshUi(Bundle bundle) {
        if (TextUtils.isEmpty(GeneralOnStartData.mOnStarWirelessInfo)) {
            this.mInfoRl.setVisibility(View.GONE);
        } else {
            this.mInfoRl.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(GeneralOnStartData.mOnStarWirelessPoint)) {
            this.mPointRl.setVisibility(View.GONE);
        } else {
            this.mPointRl.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(GeneralOnStartData.mOnStarWirelessPassword)) {
            this.mPasswordRl.setVisibility(View.GONE);
        } else {
            this.mPasswordRl.setVisibility(View.VISIBLE);
        }
        this.mInfoTv.setText(GeneralOnStartData.mOnStarWirelessInfo);
        this.mPointTv.setText(GeneralOnStartData.mOnStarWirelessPoint);
        this.mPswTv.setText(GeneralOnStartData.mOnStarWirelessPassword);
    }
}
