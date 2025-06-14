package com.hzbhd.canbus.car_cus._304.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.activity.DriveDataActivity;
import com.hzbhd.canbus.activity.HybirdActivity;
import com.hzbhd.canbus.activity.OriginalCarDeviceActivity;
import com.hzbhd.canbus.activity.SelectCanTypeActivity;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.activity.TirePressureActivity;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.adapter.MainLvAdapter;
import com.hzbhd.canbus.car_cus._304.activity.AirActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.MainListEntity;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.cantype.CanTypeUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class VehicleSettingsView extends RelativeLayout implements MainLvAdapter.ItemClickInterface {
    private List<MainListEntity> mList;
    private MainLvAdapter mMainLvAdapter;
    private RecyclerView mRecyclerView;

    public VehicleSettingsView(Context context) {
        super(context);
    }

    public VehicleSettingsView(Context context, Activity activity) {
        this(context);
        LayoutInflater.from(context).inflate(R.layout._304_view_vehicle_settings, this);
        findViews();
        initViews(activity);
    }

    private void findViews() {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
    }

    private void initViews(final Activity activity) {
        this.mList = new ArrayList();
        this.mMainLvAdapter = new MainLvAdapter(this.mList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(0);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRecyclerView.setAdapter(this.mMainLvAdapter);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = UiMgrFactory.getCanUiMgr(getContext()).getPageUiSet(getContext()).getMainPageUiSet().getBtnAction().iterator();
        while (it.hasNext()) {
            arrayList.add(new MainListEntity(it.next()));
        }
        this.mList.addAll(arrayList);
        this.mMainLvAdapter.notifyDataSetChanged();
        findViewById(R.id.v_click).setOnLongClickListener(new View.OnLongClickListener() { // from class: com.hzbhd.canbus.car_cus._304.view.VehicleSettingsView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return this.f$0.m1141xcd46ec51(activity, view);
            }
        });
        ((TextView) findViewById(R.id.tv_version)).setText(CommUtil.getVersionName(getContext()));
        if (SharePreUtil.getBoolValue(getContext(), Constant.SHARE_PRE_IS_DEBUG_MODEL, false)) {
            findViewById(R.id.ll_debug).setVisibility(0);
            findViewById(R.id.btn_switch_can).setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._304.view.VehicleSettingsView$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m1142xce7d3f30(view);
                }
            });
        }
    }

    /* renamed from: lambda$initViews$0$com-hzbhd-canbus-car_cus-_304-view-VehicleSettingsView, reason: not valid java name */
    /* synthetic */ boolean m1141xcd46ec51(Activity activity, View view) {
        SelectCanTypeUtil.showDialogToUpdate(activity, CanTypeUtil.INSTANCE.getCanType(SelectCanTypeUtil.getCurrentCanTypeId(getContext())).getList().get(0), getResources().getString(R.string.switch_car_model));
        return true;
    }

    /* renamed from: lambda$initViews$1$com-hzbhd-canbus-car_cus-_304-view-VehicleSettingsView, reason: not valid java name */
    /* synthetic */ void m1142xce7d3f30(View view) {
        getContext().startActivity(new Intent(getContext(), (Class<?>) SelectCanTypeActivity.class));
    }

    @Override // com.hzbhd.canbus.adapter.MainLvAdapter.ItemClickInterface
    public void onItemClick(int i) {
        String action = this.mList.get(i).getAction();
        action.hashCode();
        switch (action) {
            case "original_car_device":
                getContext().startActivity(new Intent(getContext(), (Class<?>) OriginalCarDeviceActivity.class));
                break;
            case "hybird":
                getContext().startActivity(new Intent(getContext(), (Class<?>) HybirdActivity.class));
                break;
            case "test":
                getContext().startActivity(new Intent(getContext(), (Class<?>) AirActivity.class));
                break;
            case "tire_info":
                getContext().startActivity(new Intent(getContext(), (Class<?>) TirePressureActivity.class));
                break;
            case "drive_data":
                getContext().startActivity(new Intent(getContext(), (Class<?>) DriveDataActivity.class));
                break;
            case "warning":
                getContext().startActivity(new Intent(getContext(), (Class<?>) WarningActivity.class));
                break;
            case "amplifier":
                getContext().startActivity(new Intent(getContext(), (Class<?>) AmplifierActivity.class));
                break;
            case "setting":
                getContext().startActivity(new Intent(getContext(), (Class<?>) SettingActivity.class));
                break;
        }
    }
}
