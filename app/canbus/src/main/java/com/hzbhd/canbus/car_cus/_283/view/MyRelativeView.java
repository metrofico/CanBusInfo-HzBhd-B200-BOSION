package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.bean.DriveData;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class MyRelativeView extends RelativeLayout {
    private MySettingDialogView ACC;
    private MySettingDialogView Air_Conditioning;
    private MySettingSelectView Comfort;
    private MySettingDialogView DCC;
    private MySettingDialogView Dynamic_Bend_lighting;
    private MySettingSelectView Eco;
    private MySettingDialogView Engine;
    private MySettingSelectView Indivdual;
    private MySettingSelectView Normal;
    private MySettingSelectView Sport;
    private MySettingDialogView Steering;
    private boolean isClick;
    private Context mContext;
    private View mView;
    private ExecutorService threadExecutor;
    private MySettingSelectView xuedi;
    private MySettingSelectView yueye;
    private MySettingSelectView yueye_personal;

    public MyRelativeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.threadExecutor = Executors.newSingleThreadExecutor();
        this.isClick = true;
        this.mContext = context;
        initView();
        initData();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
        initData();
    }

    private void initView() {
        this.Comfort = (MySettingSelectView) findViewById(R.id.Comfort);
        this.Normal = (MySettingSelectView) findViewById(R.id.Normal);
        this.Sport = (MySettingSelectView) findViewById(R.id.Sport);
        this.Eco = (MySettingSelectView) findViewById(R.id.Eco);
        this.Indivdual = (MySettingSelectView) findViewById(R.id.Indivdual);
        this.xuedi = (MySettingSelectView) findViewById(R.id.xuedi);
        this.yueye = (MySettingSelectView) findViewById(R.id.yueye);
        this.yueye_personal = (MySettingSelectView) findViewById(R.id.yueye_personal);
        this.DCC = (MySettingDialogView) findViewById(R.id.DCC);
        this.Dynamic_Bend_lighting = (MySettingDialogView) findViewById(R.id.Dynamic_Bend_lighting);
        this.Engine = (MySettingDialogView) findViewById(R.id.Engine);
        this.ACC = (MySettingDialogView) findViewById(R.id.ACC);
        this.Air_Conditioning = (MySettingDialogView) findViewById(R.id.Air_Conditioning);
        this.Steering = (MySettingDialogView) findViewById(R.id.Steering);
    }

    private void initData() {
        refreshUi();
    }

    public void refreshUi() {
        Log.d("mww", "refreshUi----------Comfort " + this.Comfort);
        this.isClick = false;
        MySettingSelectView mySettingSelectView = this.Comfort;
        if (mySettingSelectView != null) {
            mySettingSelectView.setSelect(DriveData.Comfort);
        }
        MySettingSelectView mySettingSelectView2 = this.Normal;
        if (mySettingSelectView2 != null) {
            mySettingSelectView2.setSelect(DriveData.Normal);
        }
        MySettingSelectView mySettingSelectView3 = this.Sport;
        if (mySettingSelectView3 != null) {
            mySettingSelectView3.setSelect(DriveData.Sport);
        }
        MySettingSelectView mySettingSelectView4 = this.Eco;
        if (mySettingSelectView4 != null) {
            mySettingSelectView4.setSelect(DriveData.Eco);
        }
        MySettingSelectView mySettingSelectView5 = this.Indivdual;
        if (mySettingSelectView5 != null) {
            mySettingSelectView5.setSelect(DriveData.Indivdual);
        }
        MySettingSelectView mySettingSelectView6 = this.xuedi;
        if (mySettingSelectView6 != null) {
            mySettingSelectView6.setSelect(DriveData.xuedi);
        }
        MySettingSelectView mySettingSelectView7 = this.yueye;
        if (mySettingSelectView7 != null) {
            mySettingSelectView7.setSelect(DriveData.yueye);
        }
        MySettingSelectView mySettingSelectView8 = this.yueye_personal;
        if (mySettingSelectView8 != null) {
            mySettingSelectView8.setSelect(DriveData.yueye_personal);
        }
        MySettingDialogView mySettingDialogView = this.DCC;
        if (mySettingDialogView != null) {
            mySettingDialogView.setSelect(DriveData.DCC);
        }
        MySettingDialogView mySettingDialogView2 = this.Dynamic_Bend_lighting;
        if (mySettingDialogView2 != null) {
            mySettingDialogView2.setSelect(DriveData.Dynamic_Bend_lighting);
        }
        MySettingDialogView mySettingDialogView3 = this.Engine;
        if (mySettingDialogView3 != null) {
            mySettingDialogView3.setSelect(DriveData.Engine);
        }
        MySettingDialogView mySettingDialogView4 = this.ACC;
        if (mySettingDialogView4 != null) {
            mySettingDialogView4.setSelect(DriveData.ACC);
        }
        MySettingDialogView mySettingDialogView5 = this.Air_Conditioning;
        if (mySettingDialogView5 != null) {
            mySettingDialogView5.setSelect(DriveData.Air_Conditioning);
        }
        MySettingDialogView mySettingDialogView6 = this.Steering;
        if (mySettingDialogView6 != null) {
            mySettingDialogView6.setSelect(DriveData.Steering);
        }
        this.isClick = true;
    }
}
