package com.hzbhd.canbus.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.DriveDataPvAdapter;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralDriveData;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DriveDataActivity extends AbstractBaseActivity implements View.OnClickListener {
    public static final int INVALID_INDEX = -1;
    private int leftPosition;
    private Button mBtn;
    private DriveDataPvAdapter mDriveDataPvAdapter;
    private List<DriverDataPageUiSet.Page> mList;
    private OnDriveDataPageStatusListener mOnDriveDataPageStatusListener;
    private TextView mTitleTv;
    private ViewPager mViewPager;
    private int rightPosition;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) throws Resources.NotFoundException {
        super.onCreate(bundle);
        setContentView(R.layout.activity_drive_data);
        findViews();
        initViews();
        Intent intent = getIntent();
        if (intent == null || !Constant.DRIVE_DATA_OPEN_TARGET_PAGE.equals(intent.getAction())) {
            return;
        }
        goToTheItem(intent);
    }

    private void goToTheItem(Intent intent) throws Resources.NotFoundException {
        this.mViewPager.setCurrentItem(intent.getIntExtra(Constant.CURRENT_ITEM, 0));
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) throws Resources.NotFoundException {
        super.onNewIntent(intent);
        if (Constant.DRIVE_DATA_OPEN_TARGET_PAGE.equals(intent.getAction())) {
            goToTheItem(intent);
        }
    }

    private void findViews() {
        this.mViewPager = findViewById(R.id.vp);
        this.mTitleTv = findViewById(R.id.tv_title);
        this.mBtn = findViewById(R.id.ib_btn);
    }

    private void initViews() throws Resources.NotFoundException {
        DriverDataPageUiSet driverDataPageUiSet = getUiMgrInterface(this).getDriverDataPageUiSet(this);
        OnDriveDataPageStatusListener onDriveDataPageStatusListener = driverDataPageUiSet.getOnDriveDataPageStatusListener();
        this.mOnDriveDataPageStatusListener = onDriveDataPageStatusListener;
        if (onDriveDataPageStatusListener != null) {
            onDriveDataPageStatusListener.onStatusChange(-1);
        } else {
            LogUtil.showLog("mOnDriveDataPageStatusListener==null");
        }
        this.mList = new ArrayList<>();
        this.mDriveDataPvAdapter = new DriveDataPvAdapter(this, this.mList);
        this.mList.addAll(driverDataPageUiSet.getList());
        this.mViewPager.setAdapter(this.mDriveDataPvAdapter);
        this.mTitleTv.setText(CommUtil.getStrIdByResId(this, this.mList.get(0).getHeadTitleSrn()));
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.hzbhd.canbus.activity.DriveDataActivity.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                TextView textView = DriveDataActivity.this.mTitleTv;
                DriveDataActivity driveDataActivity = DriveDataActivity.this;
                textView.setText(CommUtil.getStrIdByResId(driveDataActivity, driveDataActivity.mList.get(i).getHeadTitleSrn()));
            }
        });
        this.mBtn.setText(CommUtil.getStrIdByResId(this, driverDataPageUiSet.getButtonText()));
        if (driverDataPageUiSet.isHaveBtn()) {
            this.mBtn.setVisibility(View.VISIBLE);
            this.leftPosition = driverDataPageUiSet.getLeftPosition();
            this.rightPosition = driverDataPageUiSet.getRightPosition();
            return;
        }
        this.mBtn.setVisibility(View.GONE);
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        refreshUi(null);
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (isShouldRefreshUi()) {
            LogUtil.showLog("DriveData refreshUi:" + GeneralDriveData.dataList.size());
            List<DriverUpdateEntity> list = GeneralDriveData.dataList;
            if (list == null) {
                return;
            }
            for (DriverUpdateEntity driverUpdateEntity : list) {
                if (driverUpdateEntity.getPage() != -1 && driverUpdateEntity.getIndex() != -1 && driverUpdateEntity.getPage() < this.mList.size() && driverUpdateEntity.getIndex() < this.mList.get(driverUpdateEntity.getPage()).getItemList().size()) {
                    this.mList.get(driverUpdateEntity.getPage()).getItemList().get(driverUpdateEntity.getIndex()).setValue(driverUpdateEntity.getValue());
                }
            }
            this.mDriveDataPvAdapter.notifyDataSetChanged();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws Resources.NotFoundException {
        int currentItem = this.mViewPager.getCurrentItem();
        int id = view.getId();
        if (id == R.id.ib_btn) {
            startSettingActivity(this, this.leftPosition, this.rightPosition);
            return;
        }
        if (id != R.id.ib_last) {
            if (id != R.id.ib_next) {
                return;
            }
            if (currentItem != this.mList.size() - 1) {
                this.mViewPager.setCurrentItem(currentItem + 1);
                return;
            } else {
                this.mViewPager.setCurrentItem(0);
                return;
            }
        }
        if (currentItem != 0) {
            this.mViewPager.setCurrentItem(currentItem - 1);
            return;
        }
        try {
            this.mViewPager.setCurrentItem(this.mList.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startSettingActivity(Context context, int i, int i2) {
        Intent intent = new Intent(context, SettingActivity.class);
        intent.setAction(Constant.SETTING_OPEN_TARGET_PAGE);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.LEFT_INDEX, i);
        intent.putExtra(Constant.RIGHT_INDEX, i2);
        context.startActivity(intent);
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        OnDriveDataPageStatusListener onDriveDataPageStatusListener = this.mOnDriveDataPageStatusListener;
        if (onDriveDataPageStatusListener != null) {
            onDriveDataPageStatusListener.onStatusChange(-2);
        }
    }
}
