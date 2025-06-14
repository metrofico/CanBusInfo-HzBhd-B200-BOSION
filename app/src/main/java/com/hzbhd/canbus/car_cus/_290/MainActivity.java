package com.hzbhd.canbus.car_cus._290;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.car_cus._290.adapter.MainLeftLvAdapter;
import com.hzbhd.canbus.car_cus._290.entity.LeftItemBean;
import com.hzbhd.canbus.car_cus._290.entity.OnOffUpdateEntity;
import com.hzbhd.canbus.car_cus._290.view.AirView;
import com.hzbhd.canbus.car_cus._290.view.CarOnOffView;
import com.hzbhd.canbus.car_cus._290.view.MonitorView;
import com.hzbhd.canbus.car_cus._290.view.OtherFunctionView;
import com.hzbhd.canbus.car_cus._290.view.ReverseMonitorView;
import com.hzbhd.canbus.car_cus._290.view.SpecialEqView;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class MainActivity extends AbstractBaseActivity implements MainLeftLvAdapter.LeftItemClickInterface {
    public static final String BUNDLE_CHENGWEI_WHAT = "bundle_chengwei_what";
    public static final int MAIN_WHAT = 1001;
    public static final String SAVE_FIRST = "_273_first_open";
    public static final String TAG = "MainActivity";
    public static final int VIEW_AC_WHAT = 1003;
    public static final int VIEW_BACK_WHAT = 1006;
    public static final int VIEW_CENTER_DOOR_WHAT = 1005;
    public static final int VIEW_ON_OFF_WHAT = 1002;
    public static final int VIEW_SPECIAL_WHAT = 1004;
    private View bgView;
    private AirView mAirView;
    private CarOnOffView mCarOnOffView;
    private TextView mInCarTemTv;
    private List<LeftItemBean> mLeftList;
    private RecyclerView mLeftRecyclerView;
    private MainLeftLvAdapter mMainLeftLvAdapter;
    private MonitorView mMonitorView;
    private OtherFunctionView mOtherFunctionView;
    private TextView mOutCarTemTv;
    private ReverseMonitorView mReverseMonitorView;
    private SpecialEqView mSpecialEqView;
    private SurfaceView mSurefaceView;
    private TextView mTimeTv;
    private List<View> mViewList;
    private int mWhat;
    private boolean isFrontCameara = true;
    private Handler mHandler = new Handler() { // from class: com.hzbhd.canbus.car_cus._290.MainActivity.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                MainActivity.this.mTimeTv.setText(DateFormat.format("yyyy-MM-dd hh:mm", System.currentTimeMillis()));
            } else if (i == 2) {
                MainActivity.this.mMainLeftLvAdapter.notifyDataSetChanged();
            } else {
                if (i != 3) {
                    return;
                }
                MainActivity.this.showViewByIndex(0);
            }
        }
    };

    private void initSurfaceView() {
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MessageSender.setContext(this);
        setContentView(R.layout._290_activity_main);
        findViews();
        initViews();
        initSurfaceView();
        if (!SharePreUtil.getBoolValue(this, "_273_first_open", false)) {
            SharePreUtil.setBoolValue(this, "_273_first_open", true);
            int[] iArr = {0, 0, 0, 0, 0, 0, 0, 0};
            MessageSender.saveCommonSwitch(iArr);
            MessageSender.setCommonSwitchs(iArr);
            return;
        }
        int[] strArray2IntArray = getStrArray2IntArray(MessageSender.getCommonSwitch().split(","));
        MessageSender.setCommonSwitchs(strArray2IntArray);
        initRefreshCarOnOffView(strArray2IntArray);
        initRefreshAirView(strArray2IntArray);
        initRefreshSpecialView(strArray2IntArray);
    }

    private void initRefreshSpecialView(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OnOffUpdateEntity(0, false));
        arrayList.add(new OnOffUpdateEntity(1, false));
        arrayList.add(new OnOffUpdateEntity(2, false));
        arrayList.add(new OnOffUpdateEntity(3, false));
        arrayList.add(new OnOffUpdateEntity(4, DataHandleUtils.getIntFromByteWithBit(iArr[5], 6, 2) == 1));
        arrayList.add(new OnOffUpdateEntity(5, false));
        arrayList.add(new OnOffUpdateEntity(6, false));
        arrayList.add(new OnOffUpdateEntity(7, false));
        arrayList.add(new OnOffUpdateEntity(8, false));
        arrayList.add(new OnOffUpdateEntity(9, false));
        GeneralCwData.mSpecialEqUpdateList = arrayList;
        Bundle bundle = new Bundle();
        bundle.putInt("bundle_chengwei_what", 1004);
        refreshUi(bundle);
    }

    private void initRefreshAirView(int[] iArr) {
        GeneralCwData.air_sb = DataHandleUtils.getIntFromByteWithBit(iArr[6], 4, 2) == 1;
        GeneralCwData.air_dh = DataHandleUtils.getIntFromByteWithBit(iArr[6], 6, 2) == 1;
        GeneralCwData.air_sj = DataHandleUtils.getIntFromByteWithBit(iArr[7], 4, 2) == 1;
        GeneralCwData.air_ck = DataHandleUtils.getIntFromByteWithBit(iArr[7], 6, 2) == 1;
        GeneralCwData.air_a = DataHandleUtils.getIntFromByteWithBit(iArr[6], 0, 2) == 1;
        GeneralCwData.air_b = DataHandleUtils.getIntFromByteWithBit(iArr[6], 2, 2) == 1;
        GeneralCwData.air_hfw = DataHandleUtils.getIntFromByteWithBit(iArr[7], 0, 2) == 1;
        GeneralCwData.air_hfn = DataHandleUtils.getIntFromByteWithBit(iArr[7], 2, 2) == 1;
        Bundle bundle = new Bundle();
        bundle.putInt("bundle_chengwei_what", 1003);
        refreshUi(bundle);
    }

    private void initRefreshCarOnOffView(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                arrayList.add(new OnOffUpdateEntity(arrayList.size(), DataHandleUtils.getIntFromByteWithBit(iArr[i], 0, 2) == 1));
                arrayList.add(new OnOffUpdateEntity(arrayList.size(), false));
            } else {
                arrayList.add(new OnOffUpdateEntity(arrayList.size(), DataHandleUtils.getIntFromByteWithBit(iArr[i], 0, 2) == 1));
                arrayList.add(new OnOffUpdateEntity(arrayList.size(), DataHandleUtils.getIntFromByteWithBit(iArr[i], 2, 2) == 1));
                arrayList.add(new OnOffUpdateEntity(arrayList.size(), DataHandleUtils.getIntFromByteWithBit(iArr[i], 4, 2) == 1));
                arrayList.add(new OnOffUpdateEntity(arrayList.size(), DataHandleUtils.getIntFromByteWithBit(iArr[i], 6, 2) == 1));
            }
        }
        GeneralCwData.mOnOffUpdateList = arrayList;
        Bundle bundle = new Bundle();
        bundle.putInt("bundle_chengwei_what", 1002);
        refreshUi(bundle);
    }

    private int[] getStrArray2IntArray(String[] strArr) {
        int[] iArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            iArr[i] = Integer.parseInt(strArr[i]);
        }
        return iArr;
    }

    private void findViews() {
        this.mLeftRecyclerView = (RecyclerView) findViewById(R.id.rv_left_list);
        this.mCarOnOffView = (CarOnOffView) findViewById(R.id.v_car_on_off);
        this.mAirView = (AirView) findViewById(R.id.v_ac);
        this.mMonitorView = (MonitorView) findViewById(R.id.v_monitor);
        this.mSpecialEqView = (SpecialEqView) findViewById(R.id.v_special_eq);
        this.mReverseMonitorView = (ReverseMonitorView) findViewById(R.id.v_reverse_monitor);
        this.mOtherFunctionView = (OtherFunctionView) findViewById(R.id.v_other_function);
        this.mInCarTemTv = (TextView) findViewById(R.id.tv_in_car_tem);
        this.mTimeTv = (TextView) findViewById(R.id.tv_date_time);
        this.mOutCarTemTv = (TextView) findViewById(R.id.tv_out_car_tem);
        this.bgView = findViewById(R.id.bgView);
        this.mSurefaceView = new SurfaceView(this);
        this.mMonitorView.setManiActivity(this);
    }

    private void initViews() {
        this.mLeftList = new ArrayList();
        this.mMainLeftLvAdapter = new MainLeftLvAdapter(this, this.mLeftList, this);
        this.mLeftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mLeftRecyclerView.setAdapter(this.mMainLeftLvAdapter);
        new Thread() { // from class: com.hzbhd.canbus.car_cus._290.MainActivity.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                MainActivity.this.mLeftList.add(new LeftItemBean(R.string.chengwei_left_0, R.drawable.cw_kt01_left_ic_set_0, true));
                MainActivity.this.mLeftList.add(new LeftItemBean(R.string.chengwei_left_1, R.drawable.cw_kt01_left_ic_air_0, false));
                MainActivity.this.mLeftList.add(new LeftItemBean(R.string.chengwei_left_2, R.drawable.cw_kt01_left_ic_control_0, false));
                MainActivity.this.mLeftList.add(new LeftItemBean(R.string.chengwei_left_3, R.drawable.cw_kt01_left_ic_equip_0, false));
                MainActivity.this.mLeftList.add(new LeftItemBean(R.string.chengwei_left_4, R.drawable.cw_kt01_left_ic_camera_0, false));
                MainActivity.this.mLeftList.add(new LeftItemBean(R.string.chengwei_left_5, R.drawable.cw_kt01_left_ic_other_0, false));
                Message message = new Message();
                message.what = 2;
                MainActivity.this.mHandler.sendMessage(message);
            }
        }.start();
        new Thread() { // from class: com.hzbhd.canbus.car_cus._290.MainActivity.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                MainActivity.this.mViewList = new ArrayList();
                MainActivity.this.mViewList.add(MainActivity.this.mCarOnOffView);
                MainActivity.this.mViewList.add(MainActivity.this.mAirView);
                MainActivity.this.mViewList.add(MainActivity.this.mMonitorView);
                MainActivity.this.mViewList.add(MainActivity.this.mSpecialEqView);
                MainActivity.this.mViewList.add(MainActivity.this.mReverseMonitorView);
                MainActivity.this.mViewList.add(MainActivity.this.mOtherFunctionView);
                Message message = new Message();
                message.what = 3;
                MainActivity.this.mHandler.sendMessage(message);
            }
        }.start();
        new Timer().schedule(new TimerTask() { // from class: com.hzbhd.canbus.car_cus._290.MainActivity.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Message message = new Message();
                message.what = 1;
                MainActivity.this.mHandler.sendMessage(message);
            }
        }, 0L, 1000L);
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        LogUtil.showLog("273 refreshUi");
        if (bundle == null) {
        }
        if (GeneralCwData.in_car_temperature != 0) {
            this.mInCarTemTv.setText(getString(R.string.vehicle_tem_in) + "：" + String.valueOf(GeneralCwData.in_car_temperature) + getString(R.string.str_temp_c_unit));
        }
        if (GeneralCwData.out_car_temperature != 0) {
            this.mOutCarTemTv.setText(getString(R.string.vehicle_tem_out) + "：" + String.valueOf(GeneralCwData.out_car_temperature) + getString(R.string.str_temp_c_unit));
        }
        int i = bundle.getInt("bundle_chengwei_what");
        this.mWhat = i;
        switch (i) {
            case 1002:
                this.mCarOnOffView.refreshUi(bundle);
                break;
            case 1003:
                this.mAirView.refreshUi(bundle);
                break;
            case 1004:
                this.mSpecialEqView.refreshUi(bundle);
                break;
            case 1005:
                onLeftItemClick(2);
                break;
            case 1006:
                onLeftItemClick(4);
                break;
        }
    }

    @Override // com.hzbhd.canbus.car_cus._290.adapter.MainLeftLvAdapter.LeftItemClickInterface
    public void onLeftItemClick(int i) {
        int i2 = 0;
        while (i2 < this.mLeftList.size()) {
            this.mLeftList.get(i2).setSelected(i2 == i);
            i2++;
        }
        this.mMainLeftLvAdapter.notifyDataSetChanged();
        showViewByIndex(i);
    }

    public void onClick(View view) {
        if (view.getId() != R.id.iv_setting) {
            return;
        }
        startActivity(new Intent("android.settings.SETTINGS"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showViewByIndex(int i) {
        if (i > this.mViewList.size() - 1) {
            return;
        }
        if (i == 2) {
            if (this.mMonitorView.getVisibility() == 8) {
                this.bgView.setVisibility(0);
                this.bgView.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._290.MainActivity.5
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity.this.bgView.setVisibility(8);
                    }
                }, 500L);
                this.mReverseMonitorView.removeSurfaceView(this.mSurefaceView);
                this.mMonitorView.addSurfaceView(this.mSurefaceView);
                if (this.isFrontCameara) {
                    showFrontCamera();
                } else {
                    showCenterCamera();
                }
            }
        } else if (i == 4 && this.mReverseMonitorView.getVisibility() == 8) {
            this.bgView.setVisibility(0);
            this.bgView.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._290.MainActivity.6
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity.this.bgView.setVisibility(8);
                }
            }, 500L);
            this.mMonitorView.removeSurfaceView(this.mSurefaceView);
            this.mReverseMonitorView.addSurfaceView(this.mSurefaceView);
            showBackCamera();
        }
        for (int i2 = 0; i2 < this.mViewList.size(); i2++) {
            if (i2 == i) {
                this.mViewList.get(i2).setVisibility(0);
            } else {
                this.mViewList.get(i2).setVisibility(8);
            }
        }
    }

    public void showFrontCamera() {
        FutureUtil.instance.showFrontCamera();
    }

    public void showCenterCamera() {
        FutureUtil.instance.showCenterCamera();
    }

    public void showBackCamera() {
        FutureUtil.instance.showBackCamera();
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        FutureUtil.instance.reqMcuKey(new byte[]{-49, 1});
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        CarOnOffView carOnOffView = this.mCarOnOffView;
        if (carOnOffView != null) {
            carOnOffView.onDestroy();
        }
        AirView airView = this.mAirView;
        if (airView != null) {
            airView.onDestroy();
        }
        MonitorView monitorView = this.mMonitorView;
        if (monitorView != null) {
            monitorView.onDestroy();
        }
        OtherFunctionView otherFunctionView = this.mOtherFunctionView;
        if (otherFunctionView != null) {
            otherFunctionView.onDestroy();
        }
        ReverseMonitorView reverseMonitorView = this.mReverseMonitorView;
        if (reverseMonitorView != null) {
            reverseMonitorView.onDestroy();
        }
        SpecialEqView specialEqView = this.mSpecialEqView;
        if (specialEqView != null) {
            specialEqView.onDestroy();
        }
        MessageSender.saveCommonSwitch(MessageSender.getmCommonSwitchs());
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(268435456);
        intent.addCategory("android.intent.category.HOME");
        startActivity(intent);
        return true;
    }
}
