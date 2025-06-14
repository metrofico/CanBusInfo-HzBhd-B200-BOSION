package com.hzbhd.canbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.SettingLeftLvAdapter;
import com.hzbhd.canbus.adapter.SettingRightLvAdapter;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSetTextListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DialogUtil;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class SettingActivity extends AbstractBaseActivity implements SettingLeftLvAdapter.LeftItemClickInterface, SettingRightLvAdapter.RightItemClickInterface, SettingRightLvAdapter.RightItemTouchInterface {
    public static final int INVALID_INDEX = -1;
    private final String TAG = "SettingActivity";
    private int mCurLeftIndex;
    private List<SettingPageUiSet.ListBean> mLeftList;
    private RecyclerView mLeftRecyclerView;
    private SettingLeftLvAdapter mLeftSettingLvAdapter;
    private OnConfirmDialogListener mOnSettingConfirmDialogListener;
    private OnSettingItemClickListener mOnSettingItemClickListener;
    private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener;
    private OnSettingItemSeekbarSetTextListener mOnSettingItemSeekbarSetTextListener;
    private OnSettingItemSelectListener mOnSettingItemSelectListener;
    private OnSettingItemSwitchListener mOnSettingItemSwitchListener;
    private OnSettingItemTouchListener mOnSettingItemTouchListener;
    private OnSettingPageStatusListener mOnSettingPageStatusListener;
    private List<SettingPageUiSet.ListBean.ItemListBean> mRightList;
    private RecyclerView mRightRecyclerView;
    private SettingRightLvAdapter mRightSettingLvAdapter;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting);
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

    private void goToThePosition(Intent intent) {
        int intExtra = intent.getIntExtra(Constant.LEFT_INDEX, 0);
        int intExtra2 = intent.getIntExtra(Constant.RIGHT_INDEX, 0);
        onLeftItemClick(intExtra);
        this.mLeftRecyclerView.scrollToPosition(intExtra);
        this.mRightRecyclerView.scrollToPosition(intExtra2);
    }

    private void initViews() {
        SettingPageUiSet settingUiSet = getUiMgrInterface(this).getSettingUiSet(this);
        this.mOnSettingItemSelectListener = settingUiSet.getOnSettingItemSelectListener();
        this.mOnSettingPageStatusListener = settingUiSet.getOnSettingPageStatusListener();
        this.mOnSettingItemSeekbarSelectListener = settingUiSet.getOnSettingItemSeekbarSelectListener();
        this.mOnSettingItemClickListener = settingUiSet.getOnSettingItemClickListener();
        this.mOnSettingItemTouchListener = settingUiSet.getOnSettingItemTouchListener();
        this.mOnSettingConfirmDialogListener = settingUiSet.getOnSettingConfirmDialogListener();
        this.mOnSettingItemSwitchListener = settingUiSet.getOnSettingItemSwitchListener();
        this.mOnSettingItemSeekbarSetTextListener = settingUiSet.getOnSettingItemSeekbarSetTextListener();
        this.mLeftList = new ArrayList();
        this.mLeftSettingLvAdapter = new SettingLeftLvAdapter(this, this.mLeftList, this);
        this.mLeftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mLeftRecyclerView.setAdapter(this.mLeftSettingLvAdapter);
        this.mRightList = new ArrayList();
        this.mRightSettingLvAdapter = new SettingRightLvAdapter(this, this.mRightList, this, this);
        this.mRightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mRightRecyclerView.setAdapter(this.mRightSettingLvAdapter);
        this.mLeftList.addAll(settingUiSet.getList());
        Iterator<SettingPageUiSet.ListBean> it = this.mLeftList.iterator();
        while (it.hasNext()) {
            it.next().setIsSelected(false);
        }
        this.mLeftList.get(0).setIsSelected(true);
        this.mLeftSettingLvAdapter.notifyDataSetChanged();
        this.mRightList.addAll(this.mLeftList.get(0).getItemList());
        this.mRightSettingLvAdapter.notifyDataSetChanged();
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        LogUtil.showLog("Setting onResume");
        OnSettingPageStatusListener onSettingPageStatusListener = this.mOnSettingPageStatusListener;
        if (onSettingPageStatusListener != null) {
            onSettingPageStatusListener.onStatusChange(-1);
            this.mOnSettingPageStatusListener.onStatusChange(0);
        } else {
            LogUtil.showLog("mOnSettingPageStatusListener==null");
        }
        refreshUi(null);
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (isShouldRefreshUi()) {
            LogUtil.showLog("Setting refreshUi:" + GeneralSettingData.dataList.size());
            if (GeneralSettingData.dataList == null) {
                return;
            }
            for (SettingUpdateEntity settingUpdateEntity : new ArrayList(GeneralSettingData.dataList)) {
                if (settingUpdateEntity != null) {
                    if (this.mCurLeftIndex == settingUpdateEntity.getLeftListIndex() && isNotError(settingUpdateEntity)) {
                        int style = this.mRightList.get(settingUpdateEntity.getRightListIndex()).getStyle();
                        if (style == 0) {
                            this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValue(settingUpdateEntity.getValue());
                            this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValueStr(settingUpdateEntity.isValueStr());
                        } else if (style != 1) {
                            if (style == 2) {
                                String strValueOf = String.valueOf(settingUpdateEntity.getValue());
                                if (this.mOnSettingItemSeekbarSetTextListener != null && (settingUpdateEntity.getValue() instanceof Integer)) {
                                    strValueOf = this.mOnSettingItemSeekbarSetTextListener.onSetText(settingUpdateEntity.getLeftListIndex(), settingUpdateEntity.getRightListIndex(), ((Integer) settingUpdateEntity.getValue()).intValue());
                                }
                                this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValue(strValueOf);
                                this.mRightList.get(settingUpdateEntity.getRightListIndex()).setProgress(settingUpdateEntity.getProgress());
                            } else if (style == 3 || style == 4) {
                                this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValue(settingUpdateEntity.getValue());
                            }
                        } else if (settingUpdateEntity.getValue() instanceof Integer) {
                            List<String> valueSrnArray = this.mRightList.get(settingUpdateEntity.getRightListIndex()).getValueSrnArray();
                            if (((Integer) settingUpdateEntity.getValue()).intValue() < valueSrnArray.size() && ((Integer) settingUpdateEntity.getValue()).intValue() >= 0) {
                                this.mRightList.get(settingUpdateEntity.getRightListIndex()).setValue(valueSrnArray.get(((Integer) settingUpdateEntity.getValue()).intValue()));
                            }
                        }
                    }
                    if (isNotError(settingUpdateEntity)) {
                        this.mLeftList.get(settingUpdateEntity.getLeftListIndex()).getItemList().get(settingUpdateEntity.getRightListIndex()).setEnable(settingUpdateEntity.isEnable());
                    }
                }
            }
            this.mRightSettingLvAdapter.notifyDataSetChanged();
            this.mLeftSettingLvAdapter.notifyDataSetChanged();
        }
    }

    private boolean isNotError(SettingUpdateEntity settingUpdateEntity) {
        return settingUpdateEntity.getLeftListIndex() != -1 && settingUpdateEntity.getRightListIndex() != -1 && settingUpdateEntity.getLeftListIndex() < this.mLeftList.size() && settingUpdateEntity.getRightListIndex() < this.mLeftList.get(settingUpdateEntity.getLeftListIndex()).getItemList().size();
    }

    @Override // com.hzbhd.canbus.adapter.SettingLeftLvAdapter.LeftItemClickInterface
    public void onLeftItemClick(int i) {
        this.mCurLeftIndex = i;
        int i2 = 0;
        while (i2 < this.mLeftList.size()) {
            this.mLeftList.get(i2).setIsSelected(i2 == i);
            i2++;
        }
        this.mLeftSettingLvAdapter.notifyDataSetChanged();
        this.mRightList.clear();
        this.mRightList.addAll(this.mLeftList.get(i).getItemList());
        this.mRightSettingLvAdapter.notifyDataSetChanged();
        refreshUi(null);
        OnSettingPageStatusListener onSettingPageStatusListener = this.mOnSettingPageStatusListener;
        if (onSettingPageStatusListener != null) {
            onSettingPageStatusListener.onStatusChange(i);
        }
    }

    @Override // com.hzbhd.canbus.adapter.SettingRightLvAdapter.RightItemClickInterface
    public void onRightItemClick(final int i) {
        OnSettingItemClickListener onSettingItemClickListener = this.mOnSettingItemClickListener;
        if (onSettingItemClickListener != null) {
            onSettingItemClickListener.onClickItem(this.mCurLeftIndex, i);
        }
        int style = this.mRightList.get(i).getStyle();
        if (style == 1) {
            SettingPageUiSet.ListBean.ItemListBean itemListBean = this.mRightList.get(i);
            String[] strArr = (String[]) itemListBean.getValueSrnArray().toArray(new String[0]);
            int iIndexOf = itemListBean.getValueSrnArray().indexOf(itemListBean.getValue());
            if (iIndexOf < 0) {
                iIndexOf = 0;
            }
            for (int i2 = 0; i2 < strArr.length; i2++) {
                strArr[i2] = CommUtil.getStrByResId(this, strArr[i2]);
            }
            DialogUtil.getInstance().showListDialog(this, strArr, iIndexOf, new DialogUtil.ListDialogCallBak() { // from class: com.hzbhd.canbus.activity.SettingActivity.1
                @Override // com.hzbhd.canbus.util.DialogUtil.ListDialogCallBak
                public void callBack(int i3) {
                    if (SettingActivity.this.mOnSettingItemSelectListener != null) {
                        SettingActivity.this.mOnSettingItemSelectListener.onClickItem(SettingActivity.this.mCurLeftIndex, i, i3);
                    }
                }
            });
            return;
        }
        if (style == 2) {
            DialogUtil.getInstance().showSeekDialog(this, this.mRightList.get(i).getMin(), this.mRightList.get(i).getMax(), this.mRightList.get(i).getProgress(), this.mRightList.get(i).isProgressDraggable(), new DialogUtil.SeekbarDialogCallBak() { // from class: com.hzbhd.canbus.activity.SettingActivity.2
                @Override // com.hzbhd.canbus.util.DialogUtil.SeekbarDialogCallBak
                public void callBack(int i3) {
                    if (SettingActivity.this.mOnSettingItemSeekbarSelectListener != null) {
                        SettingActivity.this.mOnSettingItemSeekbarSelectListener.onClickItem(SettingActivity.this.mCurLeftIndex, i, i3);
                    }
                }
            }, new DialogUtil.SeekbarSetTextListener() { // from class: com.hzbhd.canbus.activity.SettingActivity.3
                @Override // com.hzbhd.canbus.util.DialogUtil.SeekbarSetTextListener
                public String onSetText(int i3) {
                    if (SettingActivity.this.mOnSettingItemSeekbarSetTextListener != null) {
                        return SettingActivity.this.mOnSettingItemSeekbarSetTextListener.onSetText(SettingActivity.this.mCurLeftIndex, i, i3);
                    }
                    return String.valueOf(i3);
                }
            });
        } else if (style == 3) {
            DialogUtil.getInstance().showConfirmDialog(this, this.mRightList.get(i).getConfirmTis(), this.mCurLeftIndex, i, this.mOnSettingConfirmDialogListener);
        } else if (style == 4 && this.mOnSettingItemSwitchListener != null) {
            this.mOnSettingItemSwitchListener.onSwitch(this.mCurLeftIndex, i, this.mRightList.get(i).getValue() instanceof Integer ? 1 - ((Integer) this.mRightList.get(i).getValue()).intValue() : 1);
        }
    }

    @Override // com.hzbhd.canbus.adapter.SettingRightLvAdapter.RightItemTouchInterface
    public void onRightItemTouch(int i, View view, MotionEvent motionEvent) {
        OnSettingItemTouchListener onSettingItemTouchListener = this.mOnSettingItemTouchListener;
        if (onSettingItemTouchListener != null) {
            onSettingItemTouchListener.onTouchItem(this.mCurLeftIndex, i, view, motionEvent);
        }
    }
}
