package com.hzbhd.canbus.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.SyncListUpdateEntity;
import com.hzbhd.canbus.entity.SyncSoftKeyUpdateEntity;
import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.interfaces.OnSyncStateChangeListener;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.view.SyncBtnItemView;
import com.hzbhd.canbus.view.SyncInfoListView;
import com.hzbhd.canbus.view.SyncKeyBoardView;
import com.hzbhd.canbus.view.SyncLeftIconView;
import com.hzbhd.canbus.view.SyncListItemView;
import com.hzbhd.canbus.view.SyncSoftKeyView;
import com.hzbhd.canbus.view.SyncTopIconIconView;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class SyncActivity extends AbstractBaseActivity implements SyncLeftIconView.OnLeftIconClickListener, SyncInfoListView.OnListItemClickListener, SyncSoftKeyView.OnSoftKeyClickListener, SyncKeyBoardView.OnKeyBoardBtnClickListener {
    private static final int MSG_EMPTY_TV_TIME = 11;
    private static final int MSG_REFRESH_TV_TIME = 10;
    private static final String TAG = "SyncActivity";
    private final long EMPTY_TV_TIME_DELAY = 5000;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.activity.SyncActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 10) {
                SyncActivity.this.mTvPlayTime.setText(GeneralSyncData.mSyncTime);
            } else {
                if (i != 11) {
                    return;
                }
                SyncActivity.this.mTvPlayTime.setText("");
            }
        }
    };
    private String[][] mKeyboardActions;
    private String[] mLeftIconActionArray;
    private LinearLayout mLlScreemNumber;
    private OnSyncItemClickListener mOnSyncItemClickListener;
    private OnSyncStateChangeListener mOnSyncStateChangeListener;
    private SyncPageUiSet mSet;
    private SyncInfoListView mSyncInfoListView;
    private SyncKeyBoardView mSyncKeyBoardView;
    private SyncLeftIconView mSyncLeftIconView;
    private SyncSoftKeyView mSyncSoftKeyView;
    private SyncTopIconIconView mSyncTopIconIconView;
    private TextView mTvPlayTime;
    private TextView mTvScreemNumber;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sync);
        findView();
        initData();
        this.mHandler.post(new Runnable() { // from class: com.hzbhd.canbus.activity.SyncActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws Resources.NotFoundException {
                this.f$0.m34lambda$onCreate$0$comhzbhdcanbusactivitySyncActivity();
            }
        });
    }

    /* renamed from: lambda$onCreate$0$com-hzbhd-canbus-activity-SyncActivity, reason: not valid java name */
    /* synthetic */ void m34lambda$onCreate$0$comhzbhdcanbusactivitySyncActivity() throws Resources.NotFoundException {
        initView();
        refreshUi(null);
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        requestAudioChannel();
        OnSyncStateChangeListener onSyncStateChangeListener = this.mOnSyncStateChangeListener;
        if (onSyncStateChangeListener != null) {
            onSyncStateChangeListener.onResume();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        OnSyncStateChangeListener onSyncStateChangeListener = this.mOnSyncStateChangeListener;
        if (onSyncStateChangeListener != null) {
            onSyncStateChangeListener.onStop();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        Log.i(TAG, "refreshUi: in");
        if (!ArrayUtils.isEmpty(GeneralSyncData.mSyncTopIconResIdArray)) {
            for (int i = 0; i < GeneralSyncData.mSyncTopIconCount; i++) {
                try {
                    this.mSyncTopIconIconView.getItem(i).setImageResource(GeneralSyncData.mSyncTopIconResIdArray[i]);
                } catch (IndexOutOfBoundsException | NullPointerException unused) {
                    Log.i(TAG, "refreshUi: count:" + GeneralSyncData.mSyncTopIconCount + ", length:" + GeneralSyncData.mSyncTopIconResIdArray.length);
                }
            }
        }
        List<SyncListUpdateEntity> list = GeneralSyncData.mInfoUpdateEntityList;
        if (!ArrayUtils.isEmpty(list)) {
            for (SyncListUpdateEntity syncListUpdateEntity : list) {
                SyncListItemView item = this.mSyncInfoListView.getItem(syncListUpdateEntity.getIndex());
                item.setItem(syncListUpdateEntity.getLeftIconResId(), syncListUpdateEntity.getInfo(), syncListUpdateEntity.getRightIconResId());
                item.setEnabled(syncListUpdateEntity.isEnable());
                item.setSelected(syncListUpdateEntity.getIndex() == GeneralSyncData.mSelectedLineIndex);
            }
        }
        List<SyncSoftKeyUpdateEntity> list2 = GeneralSyncData.mSoftKeyUpdateEntityList;
        if (!ArrayUtils.isEmpty(list2)) {
            for (SyncSoftKeyUpdateEntity syncSoftKeyUpdateEntity : list2) {
                SyncBtnItemView item2 = this.mSyncSoftKeyView.getItem(syncSoftKeyUpdateEntity.getIndex());
                item2.setText(syncSoftKeyUpdateEntity.getName());
                item2.setImageResource(syncSoftKeyUpdateEntity.getImageResId());
                item2.setSelected(syncSoftKeyUpdateEntity.isSelected());
                item2.setVisible(syncSoftKeyUpdateEntity.isVisible());
            }
        }
        if (GeneralSyncData.mIsSyncTimeChange) {
            GeneralSyncData.mIsSyncTimeChange = false;
            this.mHandler.removeMessages(11);
            this.mHandler.sendEmptyMessage(10);
            this.mHandler.sendEmptyMessageDelayed(11, 5000L);
        }
        int i2 = 0;
        while (i2 < 5) {
            setVisibilityGone(this.mSyncInfoListView.getItem(i2), i2 < GeneralSyncData.mInfoLineShowAmount);
            i2++;
        }
        setVisibilityGone(this.mSyncSoftKeyView, GeneralSyncData.mIsShowSoftKey);
        if (!Arrays.equals(this.mKeyboardActions, this.mSet.getKeyboardActions())) {
            this.mKeyboardActions = this.mSet.getKeyboardActions();
            this.mSyncKeyBoardView.removeAllViews();
            this.mSyncKeyBoardView.rebuild(this, this.mKeyboardActions);
        }
        this.mTvScreemNumber.setText(Integer.toString(GeneralSyncData.mSyncScreemNumber));
    }

    private void setVisible(View view, boolean z) {
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }

    private void setVisibilityGone(View view, boolean z) {
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    private void findView() {
        this.mSyncLeftIconView = (SyncLeftIconView) findViewById(R.id.ford_sync_left_icon);
        this.mSyncTopIconIconView = (SyncTopIconIconView) findViewById(R.id.sync_top_icon);
        this.mSyncInfoListView = (SyncInfoListView) findViewById(R.id.ford_sync_list);
        this.mSyncSoftKeyView = (SyncSoftKeyView) findViewById(R.id.ford_sync_soft_key);
        this.mTvPlayTime = (TextView) findViewById(R.id.tv_ford_sync_play_time);
        this.mSyncKeyBoardView = (SyncKeyBoardView) findViewById(R.id.ford_sync_keyboard);
        this.mLlScreemNumber = (LinearLayout) findViewById(R.id.ll_screem_number);
        this.mTvScreemNumber = (TextView) findViewById(R.id.tv_screen_num);
    }

    private void initData() {
        SyncPageUiSet syncPageUiSet = UiMgrFactory.getCanUiMgr(this).getSyncPageUiSet(this);
        this.mSet = syncPageUiSet;
        this.mLeftIconActionArray = syncPageUiSet.getLeftBtnActions();
        this.mOnSyncStateChangeListener = this.mSet.getOnSyncStateChangeListener();
        this.mOnSyncItemClickListener = this.mSet.getOnSyncItemClickListener();
        this.mKeyboardActions = this.mSet.getKeyboardActions();
    }

    private void initView() throws Resources.NotFoundException {
        this.mSyncLeftIconView.initButton(this, this.mLeftIconActionArray, this);
        this.mSyncTopIconIconView.initIcon(this, GeneralSyncData.mSyncTopIconCount);
        this.mSyncInfoListView.initItem(this, 5, this);
        this.mSyncSoftKeyView.initButton(this, 4, this);
        this.mSyncKeyBoardView.initKeyBoard(this, this.mKeyboardActions, this);
        setVisible(this.mLlScreemNumber, this.mSet.getIsShowScreemNumber());
    }

    @Override // com.hzbhd.canbus.view.SyncLeftIconView.OnLeftIconClickListener
    public void onIconClick(String str) {
        OnSyncItemClickListener onSyncItemClickListener = this.mOnSyncItemClickListener;
        if (onSyncItemClickListener != null) {
            onSyncItemClickListener.onLeftIconClick(str);
        }
    }

    @Override // com.hzbhd.canbus.view.SyncInfoListView.OnListItemClickListener
    public void onItemClick(int i) {
        OnSyncItemClickListener onSyncItemClickListener = this.mOnSyncItemClickListener;
        if (onSyncItemClickListener != null) {
            onSyncItemClickListener.onListItemClick(i);
        }
    }

    @Override // com.hzbhd.canbus.view.SyncSoftKeyView.OnSoftKeyClickListener
    public void onSoftKeyClick(int i) {
        OnSyncItemClickListener onSyncItemClickListener = this.mOnSyncItemClickListener;
        if (onSyncItemClickListener != null) {
            onSyncItemClickListener.onSoftKeyClick(i);
        }
    }

    @Override // com.hzbhd.canbus.view.SyncKeyBoardView.OnKeyBoardBtnClickListener
    public void onBtnClick(String str) {
        OnSyncItemClickListener onSyncItemClickListener = this.mOnSyncItemClickListener;
        if (onSyncItemClickListener != null) {
            onSyncItemClickListener.onKeyboardBtnClick(str);
        }
    }

    @Override // com.hzbhd.canbus.view.SyncKeyBoardView.OnKeyBoardBtnClickListener
    public void onBtnLongClick(String str) {
        OnSyncItemClickListener onSyncItemClickListener = this.mOnSyncItemClickListener;
        if (onSyncItemClickListener != null) {
            onSyncItemClickListener.onKeyboardBtnLongClick(str);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        releaseAudioChannel();
    }

    private void requestAudioChannel() {
        CommUtil.requestAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main, null);
    }

    private void releaseAudioChannel() {
        CommUtil.releaseAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main);
    }
}
