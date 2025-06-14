package com.hzbhd.canbus.car_cus._277.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._277.DialogUtil;
import com.hzbhd.canbus.car_cus._277.adapter.VehicleDiagnosisLeftLvAdapter;
import com.hzbhd.canbus.car_cus._277.adapter.VehicleDiagnosisiRightLvAdapter;
import com.hzbhd.canbus.car_cus._277.ui_set.PageUiSet;
import com.hzbhd.canbus.car_cus._277.ui_set.VehicleDiagnosisPageUiSet;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class VehicleDiagnosisActivity extends AbstractBaseActivity implements VehicleDiagnosisLeftLvAdapter.LeftItemClickInterface, VehicleDiagnosisiRightLvAdapter.RightItemClickInterface, VehicleDiagnosisiRightLvAdapter.RightItemTouchInterface {
    private List<SettingUpdateEntity> dataList;
    private int mCurLeftIndex;
    private List<VehicleDiagnosisPageUiSet.ListBean> mLeftList;
    private RecyclerView mLeftRecyclerView;
    private VehicleDiagnosisLeftLvAdapter mLeftSettingLvAdapter;
    private OnConfirmDialogListener mOnSettingConfirmDialogListener;
    private OnSettingItemClickListener mOnSettingItemClickListener;
    private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener;
    private OnSettingItemSelectListener mOnSettingItemSelectListener;
    private OnSettingItemTouchListener mOnSettingItemTouchListener;
    private OnSettingPageStatusListener mOnSettingPageStatusListener;
    private List<VehicleDiagnosisPageUiSet.ListBean.ItemListBean> mRightList;
    private RecyclerView mRightRecyclerView;
    private VehicleDiagnosisiRightLvAdapter mRightSettingLvAdapter;
    String mTopPageValue;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._277_activity_vehicle_diagnosis);
        findViews();
        initViews();
        Intent intent = getIntent();
        if (intent == null || !Constant.SETTING_OPEN_TARGET_PAGE.equals(intent.getAction())) {
            return;
        }
        goToThePosition(intent);
    }

    private void findViews() {
        this.mLeftRecyclerView = (RecyclerView) findViewById(R.id.rv_left_list);
        this.mRightRecyclerView = (RecyclerView) findViewById(R.id.rv_right_list);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Constant.SETTING_OPEN_TARGET_PAGE.equals(intent.getAction())) {
            goToThePosition(intent);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        Log.d("sswang", "onBackPressed: ");
        if (this.mLeftRecyclerView.getVisibility() == 8) {
            this.mLeftRecyclerView.setVisibility(0);
            this.mRightRecyclerView.setVisibility(8);
        } else {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void goToThePosition(Intent intent) {
        int intExtra = intent.getIntExtra(Constant.LEFT_INDEX, 0);
        int intExtra2 = intent.getIntExtra(Constant.RIGHT_INDEX, 0);
        onLeftItemClick(intExtra);
        this.mLeftRecyclerView.scrollToPosition(intExtra);
        this.mRightRecyclerView.scrollToPosition(intExtra2);
    }

    private String getJsonContent(Context context) {
        return CommUtil.getAssetsString(context, "car_ui/" + SelectCanTypeUtil.getCurrentCanTypeId(context) + ".json");
    }

    private void initViews() {
        VehicleDiagnosisPageUiSet vehicleDiagnosisPageUiSet = ((PageUiSet) new Gson().fromJson(getJsonContent(this), PageUiSet.class)).getVehicleDiagnosisPageUiSet();
        this.mOnSettingItemSelectListener = vehicleDiagnosisPageUiSet.getOnSettingItemSelectListener();
        this.mOnSettingPageStatusListener = vehicleDiagnosisPageUiSet.getOnSettingPageStatusListener();
        this.mOnSettingItemSeekbarSelectListener = vehicleDiagnosisPageUiSet.getOnSettingItemSeekbarSelectListener();
        this.mOnSettingItemClickListener = vehicleDiagnosisPageUiSet.getOnSettingItemClickListener();
        this.mOnSettingItemTouchListener = vehicleDiagnosisPageUiSet.getmOnSettingItemTouchListener();
        this.mOnSettingConfirmDialogListener = vehicleDiagnosisPageUiSet.getmOnSettingConfirmDialogListener();
        this.mLeftList = new ArrayList();
        this.mLeftSettingLvAdapter = new VehicleDiagnosisLeftLvAdapter(this, this.mLeftList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 1);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.c_268_car_set_line));
        this.mLeftRecyclerView.setLayoutManager(linearLayoutManager);
        this.mLeftRecyclerView.addItemDecoration(dividerItemDecoration);
        this.mLeftRecyclerView.setAdapter(this.mLeftSettingLvAdapter);
        this.mRightList = new ArrayList();
        this.mRightSettingLvAdapter = new VehicleDiagnosisiRightLvAdapter(this, this.mRightList, this, this);
        this.mRightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration2 = new DividerItemDecoration(this, 1);
        dividerItemDecoration2.setDrawable(getResources().getDrawable(R.drawable.c_268_car_set_line));
        this.mRightRecyclerView.addItemDecoration(dividerItemDecoration2);
        this.mRightRecyclerView.setAdapter(this.mRightSettingLvAdapter);
        this.mLeftList.addAll(vehicleDiagnosisPageUiSet.getList());
        Iterator<VehicleDiagnosisPageUiSet.ListBean> it = this.mLeftList.iterator();
        while (it.hasNext()) {
            it.next().setIsSelected(true);
        }
        this.mLeftSettingLvAdapter.notifyDataSetChanged();
        OnSettingPageStatusListener onSettingPageStatusListener = this.mOnSettingPageStatusListener;
        if (onSettingPageStatusListener != null) {
            onSettingPageStatusListener.onStatusChange(0);
        } else {
            LogUtil.showLog("mOnSettingPageStatusListener==null");
        }
        this.mRightSettingLvAdapter.notifyDataSetChanged();
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            refreshUi(null);
        } catch (Exception e) {
            LogUtil.showLog("VehicleDiagnosisActivity onResume:" + e.toString());
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (isShouldRefreshUi()) {
            LogUtil.showLog("diagnosis refreshUi" + ((bundle == null || !bundle.containsKey("head")) ? "" : " head: " + bundle.getString("head")));
            if (this.dataList == null) {
                this.dataList = new ArrayList();
            }
            this.dataList.clear();
            this.dataList.addAll(GeneralSettingData.dataList);
            if (this.dataList == null) {
                return;
            }
            for (int i = 0; i < this.dataList.size(); i++) {
                SettingUpdateEntity settingUpdateEntity = this.dataList.get(i);
                setRightList(settingUpdateEntity.getLeftListIndex());
                if (this.mRightList.size() == 0) {
                    return;
                }
                int style = this.mRightList.get(settingUpdateEntity.getRightListIndex()).getStyle();
                if (style == 0) {
                    this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValue(settingUpdateEntity.getValue());
                } else if (style == 1) {
                    this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValue(this.mRightList.get(settingUpdateEntity.getRightListIndex()).getValueSrnArray().get(((Integer) settingUpdateEntity.getValue()).intValue()));
                } else if (style == 2) {
                    this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValue(String.valueOf(settingUpdateEntity.getValue()));
                    this.mRightList.get(settingUpdateEntity.getRightListIndex()).setProgress(settingUpdateEntity.getProgress());
                } else if (style == 3) {
                    this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValue(settingUpdateEntity.getValue());
                }
            }
            setTopPageValue();
            setRightList(this.mCurLeftIndex);
            this.mRightSettingLvAdapter.notifyDataSetChanged();
        }
    }

    private void setTopPageValue() {
        for (VehicleDiagnosisPageUiSet.ListBean listBean : this.mLeftList) {
            this.mTopPageValue = "geely_e6_item_0";
            Iterator<VehicleDiagnosisPageUiSet.ListBean.ItemListBean> it = listBean.getItemList().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                VehicleDiagnosisPageUiSet.ListBean.ItemListBean next = it.next();
                if (!"geely_e6_diagnosis_item_0_70".equals(next.getTitleSrn()) && !"_268_diagnosis_page1_item42".equals(next.getTitleSrn()) && !"null_value".equals(next.getValue()) && !"geely_e6_item_0".equals(next.getValue())) {
                    this.mTopPageValue = "geely_e6_item_4";
                    break;
                }
            }
            if (listBean.setValue(this.mTopPageValue)) {
                Log.i("ljq", "setTopPageValue: left notifyDataSetChanged");
                this.mLeftSettingLvAdapter.notifyDataSetChanged();
            }
        }
    }

    private void setRightList(int i) {
        this.mRightList.clear();
        this.mRightList.addAll(this.mLeftList.get(i).getItemList());
    }

    @Override // com.hzbhd.canbus.car_cus._277.adapter.VehicleDiagnosisLeftLvAdapter.LeftItemClickInterface
    public void onLeftItemClick(int i) {
        Log.i("ljq", "onLeftItemClick: ");
        OnSettingPageStatusListener onSettingPageStatusListener = this.mOnSettingPageStatusListener;
        if (onSettingPageStatusListener != null) {
            onSettingPageStatusListener.onStatusChange(i);
        }
        this.mCurLeftIndex = i;
        setRightList(i);
        this.mRightSettingLvAdapter.notifyDataSetChanged();
        this.mRightRecyclerView.setVisibility(0);
        this.mLeftRecyclerView.setVisibility(8);
        refreshUi(null);
    }

    @Override // com.hzbhd.canbus.car_cus._277.adapter.VehicleDiagnosisiRightLvAdapter.RightItemClickInterface
    public void onRightItemClick(final int i) {
        OnSettingItemClickListener onSettingItemClickListener = this.mOnSettingItemClickListener;
        if (onSettingItemClickListener != null) {
            onSettingItemClickListener.onClickItem(this.mCurLeftIndex, i);
        }
        if (this.mRightList.get(i).getStyle() != 1) {
            return;
        }
        String[] strArr = (String[]) this.mRightList.get(i).getValueSrnArray().toArray(new String[0]);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            strArr[i2] = getString(CommUtil.getStrIdByResId(this, strArr[i2]));
        }
        DialogUtil.getInstance().showListDialog(this, strArr, new DialogUtil.ListDialogCallBak() { // from class: com.hzbhd.canbus.car_cus._277.activity.VehicleDiagnosisActivity.1
            @Override // com.hzbhd.canbus.car_cus._277.DialogUtil.ListDialogCallBak
            public void callBack(int i3) {
                VehicleDiagnosisActivity.this.mOnSettingItemSelectListener.onClickItem(VehicleDiagnosisActivity.this.mCurLeftIndex, i, i3);
            }
        });
    }

    @Override // com.hzbhd.canbus.car_cus._277.adapter.VehicleDiagnosisiRightLvAdapter.RightItemTouchInterface
    public void onRightItemTouch(int i, View view, MotionEvent motionEvent) {
        OnSettingItemTouchListener onSettingItemTouchListener = this.mOnSettingItemTouchListener;
        if (onSettingItemTouchListener != null) {
            onSettingItemTouchListener.onTouchItem(this.mCurLeftIndex, i, view, motionEvent);
        }
    }
}
