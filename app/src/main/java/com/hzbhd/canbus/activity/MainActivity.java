package com.hzbhd.canbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.R;
import com.hzbhd.canbus.adapter.MainLvAdapter;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.config.GeneralSettingsConfig;
import com.hzbhd.canbus.entity.MainListEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.MainPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.cantype.CanTypeUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class MainActivity extends AbstractBaseActivity implements MainLvAdapter.ItemClickInterface, View.OnClickListener {
    private final String TAG = "MainActivity";
    private LinearLayout mDebugLayout;
    private List<MainListEntity> mList;
    private MainLvAdapter mMainLvAdapter;
    private RecyclerView mRecyclerView;
    private TextView mTvNoCanTips;
    private TextView mVersionTv;
    private View mViewClick;

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        LogUtil.showLog("MainActivity refreshUi");
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
    }

    private void findViews() {
        this.mViewClick = findViewById(R.id.v_click);
        this.mRecyclerView = findViewById(R.id.rv_list);
        this.mVersionTv = findViewById(R.id.tv_version);
        this.mDebugLayout = findViewById(R.id.ll_debug);
        this.mTvNoCanTips = findViewById(R.id.tv_no_can_tips);
    }

    private void initViews() {
        UiMgrInterface uiMgrInterface = getUiMgrInterface(this);
        if (uiMgrInterface == null) {
            this.mTvNoCanTips.setVisibility(View.VISIBLE);
            return;
        }
        MainPageUiSet mainUiSet = uiMgrInterface.getMainUiSet(this);
        if (mainUiSet == null) {
            this.mTvNoCanTips.setVisibility(View.VISIBLE);
            return;
        }
        this.mList = new ArrayList<>();
        this.mMainLvAdapter = new MainLvAdapter(this.mList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRecyclerView.setAdapter(this.mMainLvAdapter);
        ArrayList<MainListEntity> arrayList = new ArrayList<>();
        Log.i("MainActivity", "initViews: isShowApp[" + CanbusConfig.INSTANCE.isShowApp() + "]");
        if (CanbusConfig.INSTANCE.isShowApp()) {
            Iterator<String> it = mainUiSet.getBtnAction().iterator();
            while (it.hasNext()) {
                arrayList.add(new MainListEntity(it.next()));
            }
        }
        if (com.hzbhd.util.LogUtil.log5()) {
            com.hzbhd.util.LogUtil.d("initViews(): ----general_settings");
        }
        if (com.hzbhd.util.LogUtil.log5()) {
            com.hzbhd.util.LogUtil.d("initViews(): ----" + Dependency.get(CanSettingProxy.class).getCanSettingShow());
        }
        if (Dependency.get(CanSettingProxy.class).getCanSettingShow()) {
            if (com.hzbhd.util.LogUtil.log5()) {
                com.hzbhd.util.LogUtil.d("initViews(): ----general_settings");
            }
            if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).GENERAL_SETTING_PAGE_SHOW) {
                arrayList.add(new MainListEntity(MainAction.GENERAL_SETTINGS));
            }
        }
        this.mList.addAll(arrayList);
        this.mMainLvAdapter.notifyDataSetChanged();
        this.mViewClick.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.hzbhd.canbus.activity.MainActivity.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                CanTypeAllEntity canTypeAllEntity = CanTypeUtil.INSTANCE.getCanType(SelectCanTypeUtil.getCurrentCanTypeId(MainActivity.this)).getList().get(0);
                MainActivity mainActivity = MainActivity.this;
                SelectCanTypeUtil.showDialogToUpdate(mainActivity, canTypeAllEntity, mainActivity.getResources().getString(R.string.switch_car_model));
                return true;
            }
        });
        this.mVersionTv.setText(CommUtil.getVersionName(this));
        if (SharePreUtil.getBoolValue(this, Constant.SHARE_PRE_IS_DEBUG_MODEL, false)) {
            this.mDebugLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override // com.hzbhd.canbus.adapter.MainLvAdapter.ItemClickInterface
    public void onItemClick(int i) {
        String action = this.mList.get(i).getAction();
        action.hashCode();
        switch (action) {
            case "original_car_device":
                startActivity(new Intent(this, OriginalCarDeviceActivity.class));
                break;

            case "general_settings":
                startActivity(new Intent(this, FactoryActivity.class));
                break;
            case "panel_key":
                startActivity(new Intent(this, PanelKeyActivity.class));
                break;
            case "hybird":
                startActivity(new Intent(this, HybirdActivity.class));
                break;
            case "air":
                AirActivity.clickToOpenActivity(this);
                break;
            case "sync":
                startActivity(new Intent(this, SyncActivity.class));
                break;
            case "test":
                startActivity(new Intent(this, TestActivity.class));
                break;
            case "tire_info":
                startActivity(new Intent(this, TirePressureActivity.class));
                break;
            case "drive_data":
                startActivity(new Intent(this, DriveDataActivity.class));
                break;
            case "warning":
                startActivity(new Intent(this, WarningActivity.class));
                break;
            case "amplifier":
                startActivity(new Intent(this, AmplifierActivity.class));
                break;
            case "on_start":
                startActivity(new Intent(this, OnStarActivity.class));
                break;
            case "setting":
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.btn_switch_can) {
            return;
        }
        startActivity(new Intent(this, SelectCanTypeActivity.class));
    }
}
