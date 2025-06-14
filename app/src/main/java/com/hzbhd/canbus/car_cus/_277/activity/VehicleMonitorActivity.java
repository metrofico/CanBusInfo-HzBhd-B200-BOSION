package com.hzbhd.canbus.car_cus._277.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._277._277_GeneralSettingData;
import com.hzbhd.canbus.car_cus._277.adapter.VehicleMonitorRightLvAdapter;
import com.hzbhd.canbus.car_cus._277.adapter.VihicleMonitorLeftLvAdapter;
import com.hzbhd.canbus.car_cus._277.ui_set.PageUiSet;
import com.hzbhd.canbus.car_cus._277.ui_set.VehicleMonitorPageUiSet;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class VehicleMonitorActivity extends AbstractBaseActivity implements VihicleMonitorLeftLvAdapter.LeftItemClickInterface, VehicleMonitorRightLvAdapter.RightItemClickInterface, VehicleMonitorRightLvAdapter.RightItemTouchInterface {
    private List<SettingUpdateEntity> dataList;
    private int mCurLeftIndex;
    private List<VehicleMonitorPageUiSet.ListBean> mLeftList;
    private RecyclerView mLeftRecyclerView;
    private VihicleMonitorLeftLvAdapter mLeftSettingLvAdapter;
    private OnConfirmDialogListener mOnSettingConfirmDialogListener;
    private OnSettingItemClickListener mOnSettingItemClickListener;
    private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener;
    private OnSettingItemSelectListener mOnSettingItemSelectListener;
    private OnSettingItemTouchListener mOnSettingItemTouchListener;
    private OnSettingPageStatusListener mOnSettingPageStatusListener;
    private List<VehicleMonitorPageUiSet.ListBean.ItemListBean> mRightList;
    private RecyclerView mRightRecyclerView;
    private RecyclerView mRightRecyclerView1;
    private VehicleMonitorRightLvAdapter mRightSettingLvAdapter;
    private VehicleMonitorRightLvAdapter mRightSettingLvAdapter1;

    @Override // com.hzbhd.canbus.car_cus._277.adapter.VehicleMonitorRightLvAdapter.RightItemClickInterface
    public void onRightItemClick(int i) {
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._277_activity_setting_2);
        findViews();
        initViews();
    }

    private void findViews() {
        this.mLeftRecyclerView = (RecyclerView) findViewById(R.id.rv_left_list);
        this.mRightRecyclerView = (RecyclerView) findViewById(R.id.rv_right_list);
        this.mRightRecyclerView1 = (RecyclerView) findViewById(R.id.rv_right_list1);
    }

    private String getJsonContent(Context context) {
        return CommUtil.getAssetsString(context, "car_ui/" + SelectCanTypeUtil.getCurrentCanTypeId(context) + ".json");
    }

    private void initViews() {
        VehicleMonitorPageUiSet vehicleMonitorPageUiSet = ((PageUiSet) new Gson().fromJson(getJsonContent(this), PageUiSet.class)).getVehicleMonitorPageUiSet();
        this.mOnSettingItemSelectListener = vehicleMonitorPageUiSet.getOnSettingItemSelectListener();
        this.mOnSettingPageStatusListener = vehicleMonitorPageUiSet.getOnSettingPageStatusListener();
        this.mOnSettingItemSeekbarSelectListener = vehicleMonitorPageUiSet.getOnSettingItemSeekbarSelectListener();
        this.mOnSettingItemClickListener = vehicleMonitorPageUiSet.getOnSettingItemClickListener();
        this.mOnSettingItemTouchListener = vehicleMonitorPageUiSet.getmOnSettingItemTouchListener();
        this.mOnSettingConfirmDialogListener = vehicleMonitorPageUiSet.getmOnSettingConfirmDialogListener();
        this.mLeftList = new ArrayList();
        this.mLeftSettingLvAdapter = new VihicleMonitorLeftLvAdapter(this, this.mLeftList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.mLeftRecyclerView.setLayoutManager(linearLayoutManager);
        this.mLeftRecyclerView.setAdapter(this.mLeftSettingLvAdapter);
        this.mRightList = new ArrayList();
        this.mRightSettingLvAdapter = new VehicleMonitorRightLvAdapter(this, this.mRightList, this, this);
        this.mRightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 1);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.c_268_car_set_line));
        this.mRightRecyclerView.addItemDecoration(dividerItemDecoration);
        this.mRightRecyclerView.setAdapter(this.mRightSettingLvAdapter);
        this.mRightSettingLvAdapter1 = new VehicleMonitorRightLvAdapter(this, this.mRightList, this, this);
        this.mRightRecyclerView1.setLayoutManager(new GridLayoutManager((Context) this, 2, 1, false));
        this.mRightRecyclerView1.setAdapter(this.mRightSettingLvAdapter1);
        this.mLeftList.addAll(vehicleMonitorPageUiSet.getList());
        Iterator<VehicleMonitorPageUiSet.ListBean> it = this.mLeftList.iterator();
        while (it.hasNext()) {
            it.next().setIsSelected(false);
        }
        this.mLeftList.get(0).setIsSelected(true);
        this.mLeftSettingLvAdapter.notifyDataSetChanged();
        OnSettingPageStatusListener onSettingPageStatusListener = this.mOnSettingPageStatusListener;
        if (onSettingPageStatusListener != null) {
            onSettingPageStatusListener.onStatusChange(0);
        } else {
            LogUtil.showLog("mOnSettingPageStatusListener==null");
        }
        this.mRightList.addAll(this.mLeftList.get(0).getItemList());
        this.mRightSettingLvAdapter1.notifyDataSetChanged();
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            refreshUi(null);
        } catch (Exception e) {
            LogUtil.showLog("VehicleMonitorActivity onResume:" + e.toString());
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (isShouldRefreshUi()) {
            LogUtil.showLog("monitor refreshUi" + ((bundle == null || !bundle.containsKey("head")) ? "" : " head: " + bundle.getString("head")));
            if (this.dataList == null) {
                this.dataList = new ArrayList();
            }
            this.dataList.clear();
            this.dataList.addAll(_277_GeneralSettingData.dataList2);
            if (this.dataList == null) {
                return;
            }
            for (int i = 0; i < this.dataList.size(); i++) {
                SettingUpdateEntity settingUpdateEntity = this.dataList.get(i);
                if (this.mCurLeftIndex == settingUpdateEntity.getLeftListIndex()) {
                    int style = this.mRightList.get(settingUpdateEntity.getRightListIndex()).getStyle();
                    if (style == 0) {
                        this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValue(settingUpdateEntity.getValue());
                    } else if (style == 1) {
                        this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValue(this.mRightList.get(settingUpdateEntity.getRightListIndex()).getValueSrnArray().get(((Integer) settingUpdateEntity.getValue()).intValue()));
                    } else if (style == 2) {
                        this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValue(String.valueOf(settingUpdateEntity.getValue()));
                        this.mRightList.get(settingUpdateEntity.getRightListIndex()).setProgress(settingUpdateEntity.getProgress());
                    } else if (style == 4) {
                        this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValue(settingUpdateEntity.getValue());
                    } else if (style == 5) {
                        this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValue(settingUpdateEntity.getValue());
                    }
                }
            }
            this.mRightSettingLvAdapter.notifyDataSetChanged();
            this.mRightSettingLvAdapter1.notifyDataSetChanged();
        }
    }

    @Override // com.hzbhd.canbus.car_cus._277.adapter.VihicleMonitorLeftLvAdapter.LeftItemClickInterface
    public void onLeftItemClick(int i) {
        OnSettingPageStatusListener onSettingPageStatusListener = this.mOnSettingPageStatusListener;
        if (onSettingPageStatusListener != null) {
            onSettingPageStatusListener.onStatusChange(i);
        }
        this.mCurLeftIndex = i;
        int i2 = 0;
        while (i2 < this.mLeftList.size()) {
            this.mLeftList.get(i2).setIsSelected(i2 == i);
            i2++;
        }
        if (i == 0) {
            this.mRightRecyclerView1.setVisibility(0);
            this.mRightRecyclerView.setVisibility(8);
        } else {
            this.mRightRecyclerView1.setVisibility(8);
            this.mRightRecyclerView.setVisibility(0);
        }
        this.mLeftSettingLvAdapter.notifyDataSetChanged();
        this.mRightList.clear();
        int rowNumber = this.mLeftList.get(i).getRowNumber();
        if (rowNumber > 0) {
            for (int i3 = 0; i3 < rowNumber; i3++) {
                this.mRightList.add(this.mLeftList.get(i).getItemList().get(0));
            }
        } else {
            this.mRightList.addAll(this.mLeftList.get(i).getItemList());
        }
        if (i == 0) {
            this.mRightSettingLvAdapter1.notifyDataSetChanged();
        } else {
            this.mRightSettingLvAdapter.notifyDataSetChanged();
        }
        refreshUi(null);
    }

    @Override // com.hzbhd.canbus.car_cus._277.adapter.VehicleMonitorRightLvAdapter.RightItemTouchInterface
    public void onRightItemTouch(int i, View view, MotionEvent motionEvent) {
        OnSettingItemTouchListener onSettingItemTouchListener = this.mOnSettingItemTouchListener;
        if (onSettingItemTouchListener != null) {
            onSettingItemTouchListener.onTouchItem(this.mCurLeftIndex, i, view, motionEvent);
        }
    }
}
