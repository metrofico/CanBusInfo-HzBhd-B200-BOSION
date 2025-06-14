package com.hzbhd.canbus.activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.WarningLvAdapter;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.interfaces.OnWarningStatusChangeListener;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.WarningPageUiSet;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class WarningActivity extends AbstractBaseActivity {
    public static final int STATUS_ON_RESUME = 4;
    public static final int STATUS_ON_STOP = 6;
    private List<WarningEntity> mList;
    private OnWarningStatusChangeListener mOnWarningStatusChangeListener;
    private RecyclerView mRecyclerView;
    private WarningPageUiSet mSet;
    private TextView mTisTv;
    private WarningLvAdapter mWarningLvAdapter;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_warning);
        findViews();
        initViews();
    }

    private void findViews() {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        this.mTisTv = (TextView) findViewById(R.id.tv_tis);
    }

    private void initViews() {
        WarningPageUiSet warningPageUiSet = UiMgrFactory.getCanUiMgr(this).getWarningPageUiSet(this);
        this.mSet = warningPageUiSet;
        if (warningPageUiSet != null) {
            this.mOnWarningStatusChangeListener = warningPageUiSet.getOnWarningStatusChangeListener();
        }
        this.mList = new ArrayList();
        this.mWarningLvAdapter = new WarningLvAdapter(this, this.mList);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 1);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.setting_divider));
        this.mRecyclerView.addItemDecoration(dividerItemDecoration);
        this.mRecyclerView.setAdapter(this.mWarningLvAdapter);
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        OnWarningStatusChangeListener onWarningStatusChangeListener = this.mOnWarningStatusChangeListener;
        if (onWarningStatusChangeListener != null) {
            onWarningStatusChangeListener.onStatusChange(4);
        }
        refreshUi(null);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        OnWarningStatusChangeListener onWarningStatusChangeListener = this.mOnWarningStatusChangeListener;
        if (onWarningStatusChangeListener != null) {
            onWarningStatusChangeListener.onStatusChange(6);
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (isShouldRefreshUi()) {
            LogUtil.showLog("Warning refreshUi");
            List<WarningEntity> list = GeneralWarningDataData.dataList;
            if (list == null || list.size() == 0) {
                this.mTisTv.setVisibility(0);
            } else {
                this.mTisTv.setVisibility(8);
            }
            if (list == null) {
                return;
            }
            this.mList.clear();
            this.mList.addAll(list);
            this.mWarningLvAdapter.notifyDataSetChanged();
        }
    }
}
